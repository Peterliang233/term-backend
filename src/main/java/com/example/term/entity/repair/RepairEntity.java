package com.example.term.entity.repair;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("repair")
public class RepairEntity {
    @TableId(type = IdType.AUTO)
    private Integer Id;

    private String name;

    private String phone;

    private String address;

    private String detail;

    private Integer status;

    private String uuid;
}
