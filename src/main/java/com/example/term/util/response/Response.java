package com.example.term.util.response;


import com.example.term.util.code.StatusCode;
import lombok.Data;

@Data
public class Response {
    private Integer code;
    private String message;
    private Object data;

    public Response setCode(StatusCode statusCode) {
        this.code = statusCode.getCode();
        return this;
    }

    public Response setMessage(StatusCode statusCode) {
        this.message = statusCode.getMessage();
        return this;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }
}
