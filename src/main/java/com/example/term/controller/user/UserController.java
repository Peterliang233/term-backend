package com.example.term.controller.user;


import com.example.term.dto.user.LoginDto;
import com.example.term.dto.user.RegisterDto;
import com.example.term.entity.user.UserEntity;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.interceptor.jwt.JwtFactory;
import com.example.term.service.user.UserService;
import com.example.term.util.common.CommonUtil;
import com.example.term.util.response.FormattedResponse;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@ResponseExceptionCatcher
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/login")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> Login(@Valid @RequestBody LoginDto loginDto) {
        HashMap<String, Object> data = new HashMap<>(2);

        if (!userService.loginRequest(loginDto)){
            throw new ResponseException(ResponseType.ERR_LOGIN);
        }

        // 查询用户实体
        UserEntity userEntity = userService.queryByUsername(loginDto.getUsername());

        if (userEntity == null) {
            throw new ResponseException(ResponseType.ERR_LOGIN);
        }

        if (userEntity.getPassword() == loginDto.getPassword() && userEntity.getUsername()==loginDto.getUsername()) {
            throw new ResponseException(ResponseType.ERR_LOGIN);
        }

        String token = JwtFactory.buildJwt(userEntity.getUsername(), userEntity.getType(), userEntity.getUuid());

        data.put("user", userEntity);
        data.put("token", token);

        return FormattedResponse.success(data);
    }


    @JwtAuth
    @RequestMapping("/register")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> Register(HttpServletRequest request,
                                         @Valid @RequestBody RegisterDto registerDto) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("user", userService.createUser(CommonUtil.objToInteger(request.getAttribute("userType")), registerDto));

        return FormattedResponse.success(data);
    }
}
