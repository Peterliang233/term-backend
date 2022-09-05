package com.example.term.util.response;

public record FormattedResponseBody<T>(int status, String message, T data) {
    public static <T> FormattedResponseBody<T> process(int status, String message, T data) {
        return new FormattedResponseBody<>(status, message, data);
    }
}
