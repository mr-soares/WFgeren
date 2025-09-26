package com.br.WFgeren.DTO;

public record ItensResponseDTO(
    Integer id,
    String nome,
    Integer quantidade,
    Integer categoriaId
) {}