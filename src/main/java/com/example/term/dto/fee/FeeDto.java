package com.example.term.dto.fee;


import com.example.term.entity.fee.FeeEntity;
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
public class FeeDto {
    private Integer Id;

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "电话号码不能为空")
    private String phone;

    @NotNull(message = "费用类型不能为空")
    private Integer feeType;

    @NotNull(message = "状态类型不能为空")
    private Integer status;

    public FeeEntity toEntity() {
        return FeeEntity
                .builder()
                .Id(this.Id)
                .feeType(this.feeType)
                .name(this.name)
                .phone(this.phone)
                .status(this.status)
                .build();
    }
}
