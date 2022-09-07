package com.example.term.dto.repair;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepairDto {
    private Integer Id;

    @NotBlank(message = "报修人姓名不能为空")
    private String name;

    @NotBlank(message = "报修人电话不能为空")
    private String phone;

    @NotBlank(message = "报修人居住地址不能为空")
    private String address;

    @NotBlank(message = "报修详情不能为空")
    private String detail;

    @NotBlank(message = "报修状态不能为空")
    private Integer status;
}
