package com.example.term.dto.repair;


import com.example.term.entity.repair.RepairEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepairDto {
    private Integer id;

    @NotBlank(message = "报修人姓名不能为空")
    private String name;

    @NotBlank(message = "报修人电话不能为空")
    private String phone;

    @NotBlank(message = "报修人居住地址不能为空")
    private String address;

    @NotBlank(message = "报修详情不能为空")
    private String detail;

    @NotNull(message = "报修状态不能为空")
    private Integer status;

    private String uuid;

    public RepairEntity toEntity() {
        return RepairEntity.builder()
                .address(this.address)
                .Id(this.id)
                .phone(this.phone)
                .status(this.status)
                .name(this.name)
                .detail(this.detail)
                .uuid(this.uuid)
                .build();
    }
}
