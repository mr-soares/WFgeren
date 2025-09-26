package com.br.WFgeren.Exception;

public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException(String message) {
        super(message);
    }
}