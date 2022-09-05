package com.example.term.entity.resident;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("resident")
public class ResidentEntity {
    @TableId(type = IdType.AUTO)
    private Integer Id;

    private String name;

    private String phone;

    private String address;

    private Date enterTime;
}
