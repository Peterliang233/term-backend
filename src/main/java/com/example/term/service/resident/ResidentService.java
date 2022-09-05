package com.example.term.service.resident;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.dto.resident.ResidentDto;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.mapper.resident.ResidentMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResidentService {

    @Resource
    private ResidentMapper residentMapper;


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentDto> getResidentList() {
       return null;
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public void addResident(ResidentEntity residentEntity) {
        residentMapper.insert(residentEntity);
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
    public void deleteResident(Integer id) {
        QueryWrapper<ResidentEntity> queryWrapper = new QueryWrapper<>();
        residentMapper.delete(queryWrapper.eq("id", id));
    }
}
