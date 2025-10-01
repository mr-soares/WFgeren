package com.br.WFgeren.service;

import org.springframework.stereotype.Service;

@Service
public class TokenStoreService {
    private String token;

    public void saveToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void clearToken() {
        this.token = null;
    }
}