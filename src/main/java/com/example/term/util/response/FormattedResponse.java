package com.example.term.util.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FormattedResponse<T> extends ResponseEntity<FormattedResponseBody<T>> {
    private FormattedResponse(ResponseType responseType, T data) {
        super(FormattedResponseBody.process(responseType.getStatus(), responseType.getMessage(), data),
                HttpStatus.valueOf(responseType.getCode()));
    }

    public static <T> FormattedResponse<T> process(ResponseType responseType, T data) {
        return new FormattedResponse<>(responseType, data);
    }

    public static <T> FormattedResponse<T> success(T data) {
        return new FormattedResponse<>(ResponseType.SUCCESS, data);
    }

    public static <T> FormattedResponse<T> success() {
        return new FormattedResponse<>(ResponseType.SUCCESS, null);
    }

    public static <T> FormattedResponse<T> failure() {
        return new FormattedResponse<>(ResponseType.UNKNOWN, null);
    }

    public static <T> FormattedResponse<T> failure(ResponseType responseType) {
        return new FormattedResponse<>(responseType, null);
    }
}
