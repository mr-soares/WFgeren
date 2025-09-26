package com.br.WFgeren.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarUser(
       String nome,
        @Size(min = 8, max = 20 ,message = "Mínimo de 8 dígitos e máximo de 20") String senha,
       String confirmacao
) {
}