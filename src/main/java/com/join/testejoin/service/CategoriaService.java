package com.join.testejoin.service;

import com.join.testejoin.model.categoria.CategoriaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {

    CategoriaDto cadastrar(CategoriaDto dto);
    Page<CategoriaDto> listarTodos(Pageable paginacao);
    Page<CategoriaDto> listarCatgoriasPorNome(String nome, Pageable paginacao);
    CategoriaDto listPorId(Long id);
    void excluir(Long id);
    CategoriaDto atualizar(Long id, CategoriaDto dto);
}
