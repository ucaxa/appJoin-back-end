package com.join.testejoin.model.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoDto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                            @NotNull Long categoriaId) {
}
