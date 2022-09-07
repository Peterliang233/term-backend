package com.example.term.service.repair;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.repair.RepairDto;
import com.example.term.entity.repair.RepairEntity;
import com.example.term.mapper.repair.RepairMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Service
public class RepairService {
    @Resource
    private RepairMapper repairMapper;

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<RepairEntity> getRepairList(Integer userType, String uuid) {
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 非管理员只能查到自己的uuid关联的
            QueryWrapper<RepairEntity> queryWrapper = new QueryWrapper<>();
            return repairMapper.selectList(queryWrapper.eq("uuid", uuid));
        }
        return repairMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<RepairEntity> addRepair(RepairDto repairDto, String uuid, Integer userType) {
        RepairEntity repairEntity = repairDto.toEntity();

        repairEntity.setUuid(uuid);

        repairMapper.insert(repairEntity);

        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            return repairMapper.selectList(new QueryWrapper<RepairEntity>().eq("uuid", uuid));
        }

        return repairMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<RepairEntity> updateRepair(Integer userType, String uuid, RepairDto repairDto){
        RepairEntity repairEntity = repairDto.toEntity();
        UpdateWrapper<RepairEntity> updateWrapper = new UpdateWrapper<>();
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 更新只能更新自己uuid相关的
            repairMapper.update(repairEntity, updateWrapper.eq("uuid", uuid).eq("id", repairEntity.getId()));

            return repairMapper.selectList(new QueryWrapper<RepairEntity>().eq("uuid", uuid));
        }

        repairMapper.update(repairEntity, updateWrapper.eq("id", repairEntity.getId()));

        return repairMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<RepairEntity> deleteRepair(Integer userType, String uuid, Integer id) {
        QueryWrapper<RepairEntity> queryWrapper = new QueryWrapper<>();
        // 只有管理员才可以
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            repairMapper.delete(queryWrapper.eq("uuid", uuid).eq("id", id));

            return repairMapper.selectList(new QueryWrapper<RepairEntity>().eq("uuid", uuid));
        }


        repairMapper.delete(queryWrapper.eq("id", id));

        return repairMapper.selectList(null);
    }
}
