package com.join.testejoin.model.produto;

import com.join.testejoin.datasource.entity.Produto;

import java.math.BigDecimal;

public record ProdutoListagemDto(Long id, String nome, BigDecimal valor, Long categoriaId) {

    public ProdutoListagemDto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getValor(), produto.getCategoria().getId());
    }
}
