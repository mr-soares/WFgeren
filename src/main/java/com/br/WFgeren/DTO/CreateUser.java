package com.br.WFgeren.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUser(
    @NotBlank(message = "Nome obrigatório") String nome,
   @Size(min = 8, max = 20) @NotBlank(message = "Mínimo de 8 dígitos e máximo de 20.") String senha,
    @NotBlank(message = "Confirmação de senha é obrigatório") String confirmarSenha
) { }
