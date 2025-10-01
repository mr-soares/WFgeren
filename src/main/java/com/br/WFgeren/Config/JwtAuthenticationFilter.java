package com.br.WFgeren.Config;

import com.br.WFgeren.Config.JwtService;
import com.br.WFgeren.service.TokenBlacklistService;
import com.br.WFgeren.service.TokenStoreService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenBlacklistService blacklistService;
    private final TokenStoreService tokenStoreService;

    public JwtAuthenticationFilter(JwtService jwtService, TokenBlacklistService blacklistService,
                                   TokenStoreService tokenStoreService) {
        this.jwtService = jwtService;
        this.blacklistService = blacklistService;
        this.tokenStoreService = tokenStoreService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // Se não houver header Authorization, pegar token armazenado no TokenStoreService
        if ((header == null || header.isBlank()) && tokenStoreService.getToken() != null) {
            header = "Bearer " + tokenStoreService.getToken();
        }

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // Verifica se token está na blacklist ou inválido
                if (blacklistService.isBlacklisted(token) || !jwtService.isTokenValid(token)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido ou expirado");
                    return;
                }

                String username = jwtService.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                // Guarda token no TokenStoreService para reutilização
                tokenStoreService.saveToken(token);

            } catch (Exception ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Erro ao validar token: " + ex.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}