package com.example.term.mapper;


import com.example.term.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    @Select("select * from user")
    List<UserEntity> getAllUsers();


    @Select("select * from user where username = #{username}")
    UserEntity selectUserByUsername(@Param("username")String username);




}
