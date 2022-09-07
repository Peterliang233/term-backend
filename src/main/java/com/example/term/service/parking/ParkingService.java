package com.example.term.service.parking;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.term.config.common.ConstCode;
import com.example.term.dto.parking.ParkingDto;
import com.example.term.entity.parking.ParkingEntity;
import com.example.term.mapper.parking.ParkingMapper;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseExceptionCatcher;
import com.example.term.util.response.ResponseType;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ParkingService {

    @Resource
    private ParkingMapper parkingMapper;


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ParkingEntity> getParkingList(Integer userType) {
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }
        return parkingMapper.selectList(null);
    }


    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ParkingEntity> addParking(Integer userType, ParkingDto parkingDto) {
        // 只有管理员才可以
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }
        ParkingEntity parkingEntity = parkingDto.toEntity();

        parkingMapper.insert(parkingEntity);

        return parkingMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ParkingEntity> updateParking(Integer userType, ParkingDto parkingDto){
        // 只有管理员才可以创建新的用户
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }
        ParkingEntity parkingEntity = parkingDto.toEntity();
        UpdateWrapper<ParkingEntity> updateWrapper = new UpdateWrapper<>();
        parkingMapper.update(parkingEntity, updateWrapper.eq("id", parkingEntity.getId()));

        return parkingMapper.selectList(null);
    }

    @ResponseExceptionCatcher
    @SneakyThrows
    public List<ParkingEntity> deleteParking(Integer userType, Integer id) {
        // 只有管理员才可以
        if (!Objects.equals(userType, ConstCode.ADMIN.getCode())) {
            throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
        }

        QueryWrapper<ParkingEntity> queryWrapper = new QueryWrapper<>();
        parkingMapper.delete(queryWrapper.eq("id", id));

        return parkingMapper.selectList(null);
    }
}
