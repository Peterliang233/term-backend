package com.example.term.entity.parking;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@TableName("parking")
public class ParkingEntity {
    @TableId(type = IdType.AUTO)
    private Integer Id;

    private String number;

    private Integer status;


    private String tenant;

    private String uuid;
}
