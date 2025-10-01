package com.br.WFgeren.DTO;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue; // <-- Importação chave

public record AtualizarUser(
        String nome,
        @Size(min = 8, max = 20 ,message = "Mínimo de 8 dígitos e máximo de 20") String senha,
        String confirmacao
) {
    // MÉTODO DE VALIDAÇÃO DE CLASSE (AssertTrue)
    @AssertTrue(message = "Para atualizar a senha, a nova senha e a confirmação devem coincidir.")
    public boolean isPasswordUpdateValid() {
        // Se a nova senha (senha) for nula ou vazia, significa que o usuário NÃO quer alterar, então é válido.
        if (senha == null || senha.trim().isEmpty()) {
            return true;
        }

        // Se a nova senha FOI preenchida, a confirmação (confirmacao) deve ser preenchida e ser igual à senha.
        if (confirmacao == null || confirmacao.trim().isEmpty()) {
            return false; // Senha preenchida, mas confirmação vazia/nula: INVÁLIDO
        }

        return senha.equals(confirmacao); // Compara as duas: devem ser iguais
    }
}