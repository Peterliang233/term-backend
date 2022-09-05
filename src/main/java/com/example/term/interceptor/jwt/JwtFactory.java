package com.example.term.interceptor.jwt;


import com.example.term.config.jwt.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Component
public class JwtFactory {
    private static JwtFactory factory;

    @Resource
    private JwtConfig jwtConfig;


    public static String buildJwt(String username, Integer userType) {
        long currentTimeStamp = System.currentTimeMillis();

        HashMap<String, Object> claims = new HashMap<>(2);

        claims.put("username", username);
        claims.put("userType", userType);

        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuer(factory.jwtConfig.getIssuer())
                .setIssuedAt(new Date(currentTimeStamp))
                .setExpiration(new Date(currentTimeStamp + factory.jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, factory.jwtConfig.getSecret())
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(factory.jwtConfig.getSecret())
                .parseClaimsJws(jwt)
                .getBody();
    }

    @PostConstruct
    public void init() {
        factory = this;
    }
}
