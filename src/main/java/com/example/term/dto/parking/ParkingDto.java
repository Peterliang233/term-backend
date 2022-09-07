package com.example.term.dto.parking;


import com.example.term.entity.parking.ParkingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingDto {
    private Integer Id;


    @NotBlank(message = "停车位编号不能为空")
    private String number;


    @NotBlank(message = "停车位状态不能为空")
    private Integer status;


    private String tenant;

    public ParkingEntity toEntity() {
        return ParkingEntity.builder()
                .Id(this.Id)
                .number(this.number)
                .status(this.status)
                .tenant(this.tenant)
                .build();
    }
}
