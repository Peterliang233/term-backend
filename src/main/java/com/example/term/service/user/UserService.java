package com.example.term.service.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.term.entity.user.UserEntity;
import com.example.term.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public UserEntity checkLogin(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        List<UserEntity> list = userMapper.selectList(queryWrapper.eq("username", username));
        if (list.size() != 1) {
            return null;
        }else{
            return list.get(0);
        }
    }
}
