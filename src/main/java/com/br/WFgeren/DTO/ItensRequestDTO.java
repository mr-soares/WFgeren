package com.br.WFgeren.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItensRequestDTO(
    @NotBlank String nome,
    @NotNull @Positive Integer quantidade,
    @NotNull Integer categoriaId
) {}
