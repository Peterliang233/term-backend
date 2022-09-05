package com.example.term.util.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;


@RestControllerAdvice(annotations = ResponseExceptionCatcher.class)
public class FormattedResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }


    @ExceptionHandler(ResponseException.class)
    public FormattedResponse<?> responseFormatter(ResponseException e) {
        if (e.getErrorMessage() == null) {
            return FormattedResponse.failure(e.getResponseType());
        }
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("error", e.getErrorMessage());
        return FormattedResponse.process(e.getResponseType(), data);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FormattedResponse<?> responseFormatter(MethodArgumentNotValidException e) {
        if (e.getFieldError() == null || e.getFieldError().getDefaultMessage() == null) {
            return FormattedResponse.failure();
        }
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("error", e.getFieldError().getDefaultMessage());
        return FormattedResponse.process(ResponseType.INVALID_PARAMS, data);
    }

    @ExceptionHandler(Exception.class)
    public FormattedResponse<?> responseFormatter(Exception e) {
        HashMap<String, Object> data = new HashMap<>(1);
        String message = ResponseType.UNKNOWN.getMessage();
        if (e.getCause() == null) {
            String[] messagePair = e.getMessage().split(":");
            if (messagePair.length > 0) {
                message = messagePair[0];
            }
        } else {
            message = e.getCause().getMessage();
        }
        data.put("error", message);
        return FormattedResponse.process(ResponseType.UNKNOWN, data);
    }
}
