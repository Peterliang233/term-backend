package com.example.term.service.fee;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.fee.FeeDto;
import com.example.term.entity.complaint.ComplaintEntity;
import com.example.term.entity.fee.FeeEntity;
import com.example.term.mapper.fee.FeeMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class FeeService {

    @Resource
    private FeeMapper feeMapper;


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<FeeEntity> getFeeList(Integer userType,String uuid) {
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            // 非管理员只能查询自己相关的信息
           return feeMapper.selectList(new QueryWrapper<FeeEntity>().eq("uuid", uuid));
        }
        return feeMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<FeeEntity> addFee(Integer userType, FeeDto feeDto) {
        // 只有管理员才可以
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }
        FeeEntity feeEntity = feeDto.toEntity();

        System.out.println(feeEntity);

        feeMapper.insert(feeEntity);

        return feeMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<FeeEntity> updateFee(Integer userType, FeeDto feeDto){
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }
        FeeEntity feeEntity = feeDto.toEntity();
        UpdateWrapper<FeeEntity> updateWrapper = new UpdateWrapper<>();
        feeMapper.update(feeEntity, updateWrapper.eq("id", feeEntity.getId()));

        return feeMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<FeeEntity> deleteFee(Integer userType, Integer id) {
        // 只有管理员才可以
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        QueryWrapper<FeeEntity> queryWrapper = new QueryWrapper<>();
        feeMapper.delete(queryWrapper.eq("id", id));

        return feeMapper.selectList(null);
    }
}
