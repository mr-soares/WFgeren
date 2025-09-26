package com.br.WFgeren.DTO;

import com.br.WFgeren.model.Inventario;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

public record CreateUser(
    @NotBlank(message = "Nome obrigatório") String nome,
   @Size(min = 8, max = 20) @NotBlank(message = "Mínimo de 8 dígitos e máximo de 20.") String senha,
    @NotBlank(message = "Confirmação de senha é obrigatório") String confirmarSenha
) { }
