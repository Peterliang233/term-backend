package com.example.term.service.resident;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.resident.ResidentDto;
import com.example.term.entity.complaint.ComplaintEntity;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.entity.user.UserEntity;
import com.example.term.mapper.resident.ResidentMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;

@Service
public class ResidentService {

    @Resource
    private ResidentMapper residentMapper;


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> getResidentList(Integer userType, String uuid) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 不是管理员只能返回自己uid关联的信息
            QueryWrapper<ResidentEntity> queryWrapper = new QueryWrapper<>();
            return residentMapper.selectList(queryWrapper.eq("uuid", uuid));
        }
        return residentMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> addResident(ResidentEntity residentEntity, String uuid, Integer userType) {
        residentEntity.setUuid(uuid);
        residentMapper.insert(residentEntity);
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 不是管理员只能返回自己uid关联的信息
            QueryWrapper<ResidentEntity> queryWrapper = new QueryWrapper<>();
            return residentMapper.selectList(queryWrapper.eq("uuid", uuid));
        }

        return residentMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> updateResident(ResidentEntity residentEntity, Integer userType, String uuid) {
        if (residentEntity == null) {
            throw new ResponseException(ResponseType.INVALID_PARAMS);
        }

        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 不是管理员只能返回自己uid关联的信息
            QueryWrapper<ResidentEntity> queryWrapper = new QueryWrapper<>();
            residentMapper.update(residentEntity, new UpdateWrapper<ResidentEntity>()
                    .eq("id", residentEntity.getId()).eq("uuid", uuid));
            return residentMapper.selectList(queryWrapper.eq("uuid", uuid));
        }

        UpdateWrapper<ResidentEntity> updateWrapper = new UpdateWrapper<>();
        residentMapper.update(residentEntity, updateWrapper.eq("id", residentEntity.getId()));

        return residentMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ResidentEntity> deleteResident(Integer userType, String uuid, Integer id) {
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            residentMapper.delete(new QueryWrapper<ResidentEntity>().eq("id", id).eq("uuid", uuid));

            return residentMapper.selectList(new QueryWrapper<ResidentEntity>().eq("uuid", uuid));
        }

        QueryWrapper<ResidentEntity> queryWrapper = new QueryWrapper<>();
        residentMapper.delete(queryWrapper.eq("id", id));

        return residentMapper.selectList(null);
    }
}
