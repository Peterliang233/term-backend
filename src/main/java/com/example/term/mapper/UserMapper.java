package com.example.term.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.term.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
