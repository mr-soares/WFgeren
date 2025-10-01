package com.br.WFgeren.Config;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret:}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private String base64Secret;

    @PostConstruct
    public void init() {
        if (secret == null || secret.length() < 32) {
            base64Secret = Base64.getEncoder().encodeToString(Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded());
            System.out.println("⚠️ JWT secret gerado automaticamente: " + base64Secret);
        } else {
            base64Secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
        }
    }

    public String getSecret() {
        return base64Secret;
    }

    public Long getExpiration() {
        return expiration;
    }
}