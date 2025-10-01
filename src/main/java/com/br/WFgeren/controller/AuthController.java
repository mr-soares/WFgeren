package com.br.WFgeren.controller;

import com.br.WFgeren.Config.JwtConfig;
import com.br.WFgeren.DTO.LoginRequest;
import com.br.WFgeren.Config.JwtService;
import com.br.WFgeren.service.TokenBlacklistService;
import com.br.WFgeren.service.TokenStoreService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;
    private final TokenStoreService tokenStoreService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          TokenBlacklistService tokenBlacklistService, TokenStoreService tokenStoreService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.tokenBlacklistService = tokenBlacklistService;
        this.tokenStoreService = tokenStoreService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String jwt = jwtService.generateToken(request.getUsername());
            tokenStoreService.saveToken(jwt); // Guarda token em memória
            return Map.of("token", jwt);
        }

        return Map.of("message", "Credenciais inválidas!");
    }

    @PostMapping("/logout")
    public String logout() {
        String token = tokenStoreService.getToken();
        if (token != null) {
            tokenBlacklistService.blacklistToken(token);
            tokenStoreService.clearToken(); // Limpa token guardado
            return "Logout realizado com sucesso!";
        }
        return "Nenhum token encontrado para logout!";
    }
}