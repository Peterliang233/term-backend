package com.example.term.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ResponseType {
    SUCCESS(200, 0, "成功"),

    ERROR(500, 5, "服务端错误"),
    UNKNOWN(500, 1, "未知错误"),

    ERR_LOGIN(400, 7, "用户名或者密码错误"),
    INVALID_PARAMS(400, 2, "参数有误"),

    USERNAME_CONFLICT(400, 6, "用户名冲突"),

    ERR_NOT_AUTHORIZATION(403, 4, "无权限操作"),

    ERR_TOKEN(401, 8, "token错误"),

    ERR_UUID(400, 9, "用户uuid重复"),



    NO_OPERATION_AUTHORITY(403, 3, "无权操作");


    private final Integer code;

    private final Integer status;

    private final String message;

    public static ResponseType getResponseTypeByStatus(Integer status) {
        Optional<ResponseType> optional = Arrays.stream(ResponseType.values())
                .filter(rt -> rt.getStatus().equals(status))
                .findFirst();

        return optional.orElse(ResponseType.UNKNOWN);
    }
}
