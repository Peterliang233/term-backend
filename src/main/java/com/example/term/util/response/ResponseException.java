package com.example.term.util.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends  RuntimeException {
    private ResponseType responseType;

    private  String errorMessage = null;

    public ResponseException(ResponseType responseType) {
        this.responseType = responseType;
    }
}
