package com.example.term.util.response;


import com.example.term.util.code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends  RuntimeException{
    private StatusCode statusCode;

    private String errorMessage = null;

    public ResponseException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
