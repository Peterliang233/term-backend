package com.example.term.entity.fee;


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
@TableName("fee")
public class FeeEntity {
    @TableId(type = IdType.AUTO)
    private Integer Id;

    private String name;

    private String phone;

    private Integer feeType;

    private Integer status;
}
