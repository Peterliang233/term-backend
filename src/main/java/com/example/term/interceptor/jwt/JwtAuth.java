package com.example.term.interceptor.jwt;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtAuth {
    boolean required() default true;
 }
