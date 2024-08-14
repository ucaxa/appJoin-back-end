package com.join.testejoin.model.factory;

import com.join.testejoin.datasource.entity.Produto;
import com.join.testejoin.model.produto.ProdutoDetalhamentoDto;
import com.join.testejoin.model.produto.ProdutoDto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProdutoFactory {

    public static Produto create(ProdutoDto dto) {
        return Produto.builder()
                .nome(dto.nome())
                .valor(dto.valor())
                .build();
    }
    public static Produto update(ProdutoDetalhamentoDto dto) {
        return Produto.builder()
                .id(dto.id())
                .nome(dto.nome())
                .valor(dto.valor())
                .dataCadastro(dto.dataCadastro())
                .dataUltimaAtualizacao(dto.dataUltimaAtualizacao())
                .build();
    }
}
