package com.example.term.entity.complaint;


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
@TableName("complaint")
public class ComplaintEntity {
    @TableId(type = IdType.AUTO)
    private Integer Id;

    private String name;

    private String phone;

    private String detail;

    private Integer status;

    private String uuid;
}
