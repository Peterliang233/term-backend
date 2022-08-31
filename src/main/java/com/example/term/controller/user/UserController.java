package com.example.term.controller.user;


import com.example.term.dto.user.LoginUserDto;
import com.example.term.dto.user.UserDto;
import com.example.term.entity.UserEntity;
import com.example.term.util.response.Response;
import com.example.term.util.result.ResultResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.HashMap;

@RestController
public class UserController {


    @RequestMapping("/login")
    Response Login(@RequestBody LoginUserDto loginUserDto) {
        UserEntity userEntity = loginUserDto.toEntity();
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("user", UserDto.toDto(userEntity));

        return ResultResponse.getSuccessResult(data);
    }

}
