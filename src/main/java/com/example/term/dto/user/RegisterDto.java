package com.example.term.dto.user;

import com.example.term.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotNull(message = "用户的类型不能为空")
    private Integer type;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户密码不能为空")
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .type(this.type)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
