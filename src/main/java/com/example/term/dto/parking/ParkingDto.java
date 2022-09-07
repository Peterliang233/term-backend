package com.example.term.dto.parking;


import com.example.term.entity.parking.ParkingEntity;
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
public class ParkingDto {
    private Integer id;


    @NotBlank(message = "停车位编号不能为空")
    private String number;


    @NotNull(message = "停车位状态不能为空")
    private Integer status;


    private String tenant;

    private String uuid;

    public ParkingEntity toEntity() {
        return ParkingEntity.builder()
                .Id(this.id)
                .number(this.number)
                .status(this.status)
                .tenant(this.tenant)
                .uuid(this.uuid)
                .build();
    }
}
