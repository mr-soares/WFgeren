package com.br.WFgeren.DTO;

import com.br.WFgeren.model.Inventario;

public record UsuarioInventarioDTO(
        int id,
        String nome,
        Inventario inventario
) {
}
