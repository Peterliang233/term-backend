package com.example.term.mapper;


import com.example.term.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    @Select("select * from user")
    List<User> getAllUsers();


    @Select("select * from user where username = #{username}")
    User selectUserByUsername(@Param("username")String username);




}
