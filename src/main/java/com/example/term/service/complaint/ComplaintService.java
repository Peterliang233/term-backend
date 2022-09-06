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
    public List<ComplaintEntity> getComplaintList() {
        return complaintMapper.selectList(null);
    }


    @SneakyThrows
    @ResponseExceptionCatcher
    public List<ComplaintEntity> addComplaint(ComplaintDto complaintDto) {
        ComplaintEntity complaintEntity = complaintDto.toEntity();

        complaintMapper.insert(complaintEntity);

        return complaintMapper.selectList(null);
    }


    @SneakyThrows
    @ResponseExceptionCatcher
    public List<ComplaintEntity> updateComplaint(ComplaintDto complaintDto) {

        ComplaintEntity complaintEntity = complaintDto.toEntity();

        if (complaintEntity == null) {
            throw new ResponseException(ResponseType.ERROR);
        }

        System.out.println(complaintEntity);

        UpdateWrapper<ComplaintEntity> updateWrapper = new UpdateWrapper<>();

        complaintMapper.update(complaintEntity, updateWrapper.eq("id", complaintEntity.getId()));

        return complaintMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ComplaintEntity> deleteComplaint(Integer userType, Integer id) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        QueryWrapper<ComplaintEntity> queryWrapper = new QueryWrapper<>();
        complaintMapper.delete(queryWrapper.eq("id", id));

        return complaintMapper.selectList(null);
    }
}
