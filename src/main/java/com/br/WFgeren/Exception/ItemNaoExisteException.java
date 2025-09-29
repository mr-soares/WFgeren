package com.br.WFgeren.Exception;

public class ItemNaoExisteException extends RuntimeException {
    public ItemNaoExisteException(String message) {
        super(message);
    }
}