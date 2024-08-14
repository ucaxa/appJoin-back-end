package com.join.testejoin.controller;

import com.join.testejoin.model.categoria.CategoriaDto;
import com.join.testejoin.model.produto.ProdutoDetalhamentoDto;
import com.join.testejoin.model.produto.ProdutoDto;
import com.join.testejoin.model.produto.ProdutoListagemDto;
import com.join.testejoin.service.ProdutoService;
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
import java.util.HashMap;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDetalhamentoDto> cadastrar(@RequestBody @Valid ProdutoDto dto, UriComponentsBuilder uriBuilder) {
        var produtoDto = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoDto.id()).toUri();
        return ResponseEntity.created(endereco).body(produtoDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoListagemDto>> listarTodos(@PageableDefault Pageable paginacao) {
        Page<ProdutoListagemDto> produtos = service.listarTodos(paginacao);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/buscarPorNome")
    public ResponseEntity<Page<ProdutoListagemDto>> listarTodosPorNome(@RequestParam String nome, @PageableDefault Pageable paginacao) {
        Page<ProdutoListagemDto> produtos = service.listarProdutoPorNome(nome, paginacao);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> listarPorId(@PathVariable @NotNull Long id) {
        var categoriaDto = service.listPorId(id);
        return ResponseEntity.ok(categoriaDto);
    }

  /*  @PatchMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> atualizar(@PathVariable @NotNull Long id, @RequestBody HashMap<String, Object> updates)
            throws NoSuchFieldException, IllegalAccessException {
        var produtoDetalhamento = service.atualizar(id, updates);
        return ResponseEntity.ok(produtoDetalhamento);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid ProdutoDetalhamentoDto dto) {
        var produtoDetalhamentoDtoDto = service.atualizar(id, dto);
        return ResponseEntity.ok(produtoDetalhamentoDtoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> exluir(@PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
