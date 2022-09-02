package com.example.term.dto.user;


import com.example.term.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户密码不能为空")
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
