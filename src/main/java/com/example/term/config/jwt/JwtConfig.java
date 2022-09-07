package com.example.term.config.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;

    private String issuer;

    private Long expiration;

    private String prefix;

    private String publicUsername;

    private Integer role;

    private String uuid;
}
