package com.example.term.interceptor.jwt;


import com.example.term.config.jwt.JwtConfig;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.interceptor.jwt.JwtFactory;
import com.example.term.util.response.ResponseException;
import com.example.term.util.response.ResponseType;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

@Component
public class JwtHandlerInterceptor implements HandlerInterceptor {
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response,@NotNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtAuth.class)) {
            if (method.getAnnotation(JwtAuth.class).required()) {
                String token = request.getHeader("Authorization");
                if (token == null) {
                    request.setAttribute("userType", jwtConfig.getRole());
                    request.setAttribute("username", jwtConfig.getPublicUsername());
                    return true;
                }
                if(!token.startsWith(jwtConfig.getPrefix()) || token.length() <= jwtConfig.getPrefix().length()) {
                    throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
                }

                try {
                    Claims claims = JwtFactory.parseJwt(token.split(" ")[1]);
                    Object username = claims.get("username");
                    Object userType = claims.get("userType");
                    if(userType == null || username == null) {
                        throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
                    }else{
                        request.setAttribute("username", username);
                        request.setAttribute("userType", userType);
                        return true;
                    }
                }catch (Exception e) {
                    throw new ResponseException(ResponseType.ERR_NOT_AUTHORIZATION);
                }
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
