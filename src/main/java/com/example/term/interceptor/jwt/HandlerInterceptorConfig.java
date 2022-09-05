package com.example.term.interceptor.jwt;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class HandlerInterceptorConfig implements WebMvcConfigurer {
    @Resource
    private JwtHandlerInterceptor jwtHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtHandlerInterceptor)
                .addPathPatterns("/**");
    }
}
