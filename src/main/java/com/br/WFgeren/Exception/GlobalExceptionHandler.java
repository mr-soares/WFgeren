package com.br.WFgeren.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SenhaUsuarioException.class)
    public ResponseEntity<Map<String, Object>> handleSenhaUsuarioException(SenhaUsuarioException handleSenhaUsuarioException){
        Map<String, Object> error = new HashMap<>();
        error.put("Hora: ", LocalDate.now());
        error.put("Status: ", HttpStatus.BAD_REQUEST.value());
        error.put("Error: ","Senha inválida");
        error.put("mensagem",handleSenhaUsuarioException.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NomeUsuarioException.class)
    public ResponseEntity<Map<String, Object>> handleNomeUsuarioException(NomeUsuarioException handleNomeUsuarioException){
        Map<String, Object> error = new HashMap<>();
        error.put("Hora: ", LocalDate.now());
        error.put("Status: ", HttpStatus.BAD_REQUEST.value());
        error.put("Error: ","Usuário com esse nome, já existi.");
        error.put("mensagem",handleNomeUsuarioException.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<Map<String, Object>> handleNaoExisteUsuarioException(UsuarioNaoExisteException handleUsuarioNaoExisteException){
        Map<String, Object> error = new HashMap<>();
        error.put("Hora: ", LocalDate.now());
        error.put("Status: ", HttpStatus.BAD_REQUEST.value());
        error.put("Error: ","Usuário não existe.");
        error.put("mensagem",handleUsuarioNaoExisteException.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
