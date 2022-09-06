package com.example.term.dto.resident;


import com.example.term.entity.resident.ResidentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {

    private Integer id;

    @NotBlank(message = "业主姓名不能为空")
    private String name;

    @NotBlank(message =  "业主电话号码不能为空")
    private String phone;

    @NotBlank(message = "业主电话号码不能为空")
    private String address;


    @NotNull(message = "业主入住时间不能为空")
    private Date enterTime;

    public ResidentEntity toResidentEntity() {
        return ResidentEntity.builder()
                .address(this.address)
                .name(this.name)
                .phone(this.phone)
                .enterTime(this.enterTime)
                .build();
    }

    public ResidentEntity toUpdateResidentEntity() {
        return ResidentEntity.builder()
                .Id(this.id)
                .address(this.address)
                .name(this.name)
                .phone(this.phone)
                .enterTime(this.enterTime)
                .build();
    }
}
