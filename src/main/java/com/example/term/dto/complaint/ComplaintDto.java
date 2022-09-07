package com.example.term.dto.complaint;

import com.example.term.entity.complaint.ComplaintEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {

    private Integer id;

    @NotBlank(message = "投诉人姓名不能为空")
    private String name;

    @NotBlank(message = "投诉人电话不能为空")
    private String phone;

    @NotBlank(message = "投诉详情不能为空")
    private String detail;

    @NotNull(message = "投诉状态不能为空")
    private Integer status;

    private String uuid;


    public ComplaintEntity toEntity() {
        return ComplaintEntity
                .builder()
                .Id(this.id)
                .name(this.name)
                .phone(this.phone)
                .detail(this.detail)
                .status(this.status)
                .uuid(this.uuid)
                .build();
    }
}
