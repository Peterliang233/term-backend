package com.example.term.service.resident;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.resident.ResidentDto;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.entity.user.UserEntity;
import com.example.term.mapper.resident.ResidentMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ResidentService {

    @Resource
    private ResidentMapper residentMapper;


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> getResidentList() {
       return residentMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> addResident(ResidentEntity residentEntity) {
        residentMapper.insert(residentEntity);

        return residentMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public void updateResident(ResidentEntity residentEntity) {
        if (residentEntity == null) {
            throw new ResponseException(ResponseType.INVALID_PARAMS);
        }
        UpdateWrapper<ResidentEntity> updateWrapper = new UpdateWrapper<>();
        residentMapper.update(residentEntity, updateWrapper.eq("id", residentEntity.getId()));
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> deleteResident(Integer userType, Integer id) {
        System.out.println(userType);
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        QueryWrapper<ResidentEntity> queryWrapper = new QueryWrapper<>();
        residentMapper.delete(queryWrapper.eq("id", id));

        return residentMapper.selectList(null);
    }
}
