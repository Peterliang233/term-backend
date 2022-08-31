package com.example.term.dto.user;


import com.example.term.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;


    private String password;

    public static UserDto toDto(UserEntity userEntity) {
        return new UserDto(userEntity.getUsername(), userEntity.getPassword());
    }
}
