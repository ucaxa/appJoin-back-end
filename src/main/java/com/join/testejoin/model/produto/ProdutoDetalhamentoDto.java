package com.join.testejoin.model.produto;

import com.join.testejoin.datasource.entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoDetalhamentoDto(Long id, String nome, BigDecimal valor, LocalDateTime dataCadastro,
                                     LocalDateTime dataUltimaAtualizacao, Long categoriaId) {

    public ProdutoDetalhamentoDto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getValor(), produto.getDataCadastro(), produto.getDataUltimaAtualizacao(),
                produto.getCategoria().getId());
    }
}
