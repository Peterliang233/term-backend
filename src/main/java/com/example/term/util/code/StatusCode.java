package com.example.term.util.code;


public enum StatusCode {

    SUCCESS(0, "请求成功"),
    FAIL(-1, "请求失败"),
    ERR_INTERVAL(5000, "内部错误");


    private Integer code;
    private String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
