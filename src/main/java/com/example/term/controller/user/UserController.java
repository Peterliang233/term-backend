package com.example.term.controller.user;


import com.example.term.dto.user.UserDto;
import com.example.term.entity.UserEntity;
import com.example.term.service.user.UserService;
import com.example.term.util.response.Response;
import com.example.term.util.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    Response Login(@RequestBody UserDto userDto) {
        UserEntity queryUser = userDto.toEntity();
        HashMap<String, Object> data = new HashMap<>(1);


        UserEntity user = userService.checkLogin(queryUser.getUsername());

        if (user != null && user.getPassword().equals(queryUser.getPassword())) {
            data.put("user", user);
            return ResultResponse.getSuccessResult(data);
        }else{
            data.put("user",null);
            return ResultResponse.getFailResult(data);
        }
    }
}
