package com.join.testejoin.service;

import com.join.testejoin.model.produto.ProdutoDetalhamentoDto;
import com.join.testejoin.model.produto.ProdutoDto;
import com.join.testejoin.model.produto.ProdutoListagemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface ProdutoService {

    ProdutoDetalhamentoDto cadastrar(ProdutoDto dto);
    Page<ProdutoListagemDto> listarTodos(Pageable paginacao);
    Page<ProdutoListagemDto> listarProdutoPorNome(String nome, Pageable paginacao);
    ProdutoDetalhamentoDto listPorId(Long id);
    void excluir(Long id);
    public ProdutoDetalhamentoDto atualizar(Long id, ProdutoDetalhamentoDto dto);
    }
