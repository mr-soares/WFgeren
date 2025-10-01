package com.br.WFgeren.controller;

import com.br.WFgeren.Config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/api/jwt")
public class JwtTestController {

    private final JwtConfig jwtConfig;

    public JwtTestController(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @GetMapping("/teste-jwt")
    public String testeJwt() {
        return "JWT funcionando!";
    }

}