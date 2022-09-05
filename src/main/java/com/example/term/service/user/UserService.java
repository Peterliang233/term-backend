package com.example.term.service.user;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.user.LoginDto;
import com.example.term.dto.user.RegisterDto;
import com.example.term.entity.user.UserEntity;
import com.example.term.mapper.user.UserMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    @Transactional
    @SneakyThrows
    public Boolean loginRequest(LoginDto loginDto) {
        UserEntity userEntity = loginDto.toEntity();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        List<UserEntity> list = userMapper.selectList(queryWrapper.eq("username", userEntity.getUsername())
                .eq("password", userEntity.getPassword()));
        return list.size() == 1;
    }

    @Transactional
    @SneakyThrows
    public UserEntity queryByUsername(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        return userMapper.selectOne(queryWrapper.eq("username", username));
    }

    @Transactional
    @SneakyThrows
    public List<UserEntity> createUser(Integer type, RegisterDto registerDto) {
        UserEntity userEntity = registerDto.toEntity();

        // 只有管理员才可以创建新的用户
        if (!Objects.equals(type, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }


        // 需要确保用户名唯一
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        List<UserEntity> list = userMapper.selectList(queryWrapper.eq("username", registerDto.getUsername()));
        if (list.size() != 0) {
            throw new ResponseException(ResponseType.USERNAME_CONFLICT);
        }



        userMapper.insert(userEntity);

        return userMapper.selectList(null);
    }
}
