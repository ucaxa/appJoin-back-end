package com.join.testejoin.controller;


import com.join.testejoin.model.categoria.CategoriaDto;
import com.join.testejoin.service.CategoriaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaDto dto, UriComponentsBuilder uriBuilder) {
        var categoriaDto = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/categorias/{id}").buildAndExpand(categoriaDto.id()).toUri();
        return ResponseEntity.created(endereco).body(categoriaDto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> listarTodos(@PageableDefault Pageable paginacao) {
        Page<CategoriaDto> categorias = service.listarTodos(paginacao);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping(value = "/buscarCategoriaPorNome/{nome}")
    public ResponseEntity<Page<CategoriaDto>> listarTodosPorNome(@PathVariable("nome") String nome, @PageableDefault Pageable paginacao) {
        Page<CategoriaDto> categorias = service.listarCatgoriasPorNome(nome, paginacao);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> listarPorId(@PathVariable @NotNull Long id) {
        CategoriaDto categoriaDto = service.listPorId(id);
        return ResponseEntity.ok(categoriaDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid CategoriaDto dto) {
        var categoriaDto = service.atualizar(id, dto);
        return ResponseEntity.ok(categoriaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDto> excluir(@PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
