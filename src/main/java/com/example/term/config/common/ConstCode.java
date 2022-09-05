package com.example.term.config.common;

public enum ConstCode {
    ADMIN(0),
    COMMON(1);

    private Integer code;

    ConstCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
