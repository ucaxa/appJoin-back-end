package com.join.testejoin.model.categoria;


import com.join.testejoin.datasource.entity.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDto(Long id, @NotBlank String nome) {

    public CategoriaDto(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
