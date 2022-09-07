package com.example.term.service.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.user.LoginDto;
import com.example.term.dto.user.RegisterDto;
import com.example.term.entity.user.UserEntity;
import com.example.term.mapper.user.UserMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
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
    public List<UserEntity> getUserList(Integer userType) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        return userMapper.selectList(null);
    }


    @Transactional
    @SneakyThrows
    public List<UserEntity> updateUser(Integer userType, RegisterDto registerDto) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        UserEntity userEntity = registerDto.toEntity();
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        userMapper.update(userEntity, updateWrapper.eq("id", userEntity.getId()));

        return userMapper.selectList(null);
    }


    @Transactional
    @SneakyThrows
    public List<UserEntity> deleteUser(Integer userType, Integer id) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        userMapper.delete(queryWrapper.eq("id", id));

        return userMapper.selectList(null);
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

        // 需要保证用户uID唯一
        if (userMapper.selectOne(new QueryWrapper<UserEntity>().eq("uuid", registerDto.getUuid())) != null) {
            throw new ResponseException(ResponseType.ERR_UUID);
        }



        userMapper.insert(userEntity);

        return userMapper.selectList(null);
    }
}
