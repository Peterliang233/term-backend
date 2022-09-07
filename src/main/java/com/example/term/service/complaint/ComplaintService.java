package com.example.term.service.complaint;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.complaint.ComplaintDto;
import com.example.term.entity.complaint.ComplaintEntity;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.mapper.complaint.ComplaintMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ComplaintService {

    @Resource
    private ComplaintMapper complaintMapper;


    @SneakyThrows
    @ResponseExceptionCatcher
    public List<ComplaintEntity> getComplaintList(Integer userType, String uuid) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 不是管理员只能返回自己uid关联的信息
            QueryWrapper<ComplaintEntity> queryWrapper = new QueryWrapper<>();
            return complaintMapper.selectList(queryWrapper.eq("uuid", uuid));
        }
        return complaintMapper.selectList(null);
    }


    @SneakyThrows
    @ResponseExceptionCatcher
    public List<ComplaintEntity> addComplaint(ComplaintDto complaintDto,String uuid, Integer userType) {
        ComplaintEntity complaintEntity = complaintDto.toEntity();
        complaintEntity.setUuid(uuid);

        complaintMapper.insert(complaintEntity);

        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 不是管理员只能返回自己uid关联的信息
            QueryWrapper<ComplaintEntity> queryWrapper = new QueryWrapper<>();
            return complaintMapper.selectList(queryWrapper.eq("uuid", uuid));
        }

        return complaintMapper.selectList(null);
    }


    @SneakyThrows
    @ResponseExceptionCatcher
    public List<ComplaintEntity> updateComplaint(ComplaintDto complaintDto, String uuid, Integer userType) {
        ComplaintEntity complaintEntity = complaintDto.toEntity();
        if (complaintEntity == null) {
            throw new ResponseException(ResponseType.ERROR);
        }

        // 不是管理员，只能更新自己相关的数据
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            QueryWrapper<ComplaintEntity> queryWrapper = new QueryWrapper<>();
            complaintMapper.update(complaintEntity, queryWrapper.eq("uuid", uuid).eq("id",complaintEntity.getId()));

            return complaintMapper.selectList(new QueryWrapper<ComplaintEntity>().eq("uuid", uuid));
        }



        UpdateWrapper<ComplaintEntity> updateWrapper = new UpdateWrapper<>();

        complaintMapper.update(complaintEntity, updateWrapper.eq("id", complaintEntity.getId()));

        return complaintMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ComplaintEntity> deleteComplaint(Integer userType,String uuid, Integer id) {
        QueryWrapper<ComplaintEntity> queryWrapper = new QueryWrapper<>();
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 不是管理员，只能删除自己相关的信息
            complaintMapper.delete(queryWrapper.eq("id", id).eq("uuid", uuid));
            System.out.println("debug:" +uuid);
            return complaintMapper.selectList(new QueryWrapper<ComplaintEntity>().eq("uuid", uuid));
        }


        complaintMapper.delete(queryWrapper.eq("id", id));
        return complaintMapper.selectList(null);
    }
}
