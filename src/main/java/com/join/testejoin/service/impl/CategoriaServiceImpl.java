package com.join.testejoin.service.impl;

import com.join.testejoin.datasource.entity.Categoria;
import com.join.testejoin.datasource.repository.CategoriaRepository;
import com.join.testejoin.model.categoria.CategoriaDto;
import com.join.testejoin.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    @Override
    public CategoriaDto cadastrar(CategoriaDto dto) {
        var categoria = new Categoria(dto);
        repository.save(categoria);
        return new CategoriaDto(categoria);
    }

    @Override
    public Page<CategoriaDto> listarTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(CategoriaDto::new);
    }

    @Override
    public Page<CategoriaDto> listarCatgoriasPorNome(String nome, Pageable paginacao) {
        return repository
                .findByNomeContainingIgnoreCase(nome, paginacao)
                .map(CategoriaDto::new);
    }

    @Override
    public CategoriaDto listPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return new CategoriaDto(categoria);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoriaDto atualizar(Long id, CategoriaDto dto) {
        var categoria = new Categoria(dto);
        categoria.setId(id);
        repository.save(categoria);
        return new CategoriaDto(categoria);
    }
}
