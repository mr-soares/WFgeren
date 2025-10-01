package com.br.WFgeren.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue; // <-- Importação chave

public record CreateUser(
        @NotBlank(message = "Nome obrigatório") String nome,
        @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 dígitos.")
        @NotBlank(message = "A senha é obrigatória.") String senha,
        @NotBlank(message = "Confirmação de senha é obrigatória.") String confirmarSenha
) {
    // MÉTODO DE VALIDAÇÃO DE CLASSE (AssertTrue)
    @AssertTrue(message = "As senhas não coincidem.")
    public boolean isPasswordConfirmed() {
        if (senha == null || confirmarSenha == null) {
            return false;
        }
        return senha.equals(confirmarSenha);
    }
}
