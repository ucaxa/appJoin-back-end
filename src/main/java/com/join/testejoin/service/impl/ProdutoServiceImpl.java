package com.join.testejoin.service.impl;

import com.join.testejoin.datasource.entity.Categoria;
import com.join.testejoin.datasource.entity.Produto;
import com.join.testejoin.datasource.repository.CategoriaRepository;
import com.join.testejoin.datasource.repository.ProdutoRepository;
import com.join.testejoin.model.categoria.CategoriaDto;
import com.join.testejoin.model.factory.ProdutoFactory;
import com.join.testejoin.model.produto.ProdutoDetalhamentoDto;
import com.join.testejoin.model.produto.ProdutoDto;
import com.join.testejoin.model.produto.ProdutoListagemDto;
import com.join.testejoin.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoDetalhamentoDto cadastrar(ProdutoDto dto) {
        var categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(EntityNotFoundException::new);
        var produto = ProdutoFactory.create(dto);
        produto.setCategoria(categoria);
        produto.setDataCadastro(LocalDateTime.now());
        repository.save(produto);
        return new ProdutoDetalhamentoDto(produto);
    }

    @Override
    public Page<ProdutoListagemDto> listarTodos(Pageable paginacao) {
        return produtoRepository
                .findAll(paginacao)
                .map(ProdutoListagemDto::new);
    }

    @Override
    public Page<ProdutoListagemDto> listarProdutoPorNome(String nome, Pageable paginacao) {
        return repository
                .findByNomeContainingIgnoreCase(nome, paginacao)
                .map(ProdutoListagemDto::new);
    }

    @Override
    public ProdutoDetalhamentoDto listPorId(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new ProdutoDetalhamentoDto(produto);
    }

    @Override
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

   /* @Override
    public ProdutoDetalhamentoDto atualizar(Long id, HashMap<String, Object> updates) throws NoSuchFieldException, IllegalAccessException {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

            updates.forEach((chave, valor) -> {
            Field campo = ReflectionUtils.findField(Produto.class, chave);
            campo.setAccessible(true);

            // Converter o valor se necessário (para BigDecimal e LocalDate)
            if (campo.getType().equals(BigDecimal.class)) {
                valor = new BigDecimal(valor.toString());
            } else if (campo.getType().equals(LocalDate.class)) {
                valor = LocalDate.parse(valor.toString());
            }

            ReflectionUtils.setField(campo, produto, valor);
        });


        repository.save(produto);
        return new ProdutoDetalhamentoDto(produto);
    }*/



 /*   @Override
    @Transactional
    public ProdutoDetalhamentoDto atualizar(Long id, HashMap<String, Object> updates) throws NoSuchFieldException, IllegalAccessException {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            Field field = Produto.class.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(produto, entry.getValue());


        }

        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        repository.save(produto);
        return new ProdutoDetalhamentoDto(produto);
    }*/

  /*  @Override
    @Transactional
    public ProdutoDetalhamentoDto atualizar(Long id, Map<String, Object> atualizacoes) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

            atualizacoes.forEach((chave, valor) -> {
            Field campo = ReflectionUtils.findField(Produto.class, chave);
            campo.setAccessible(true);

            // Converter o valor se necessário (para BigDecimal e LocalDate)
            if (campo.getType().equals(BigDecimal.class)) {
                valor = new BigDecimal(valor.toString());
            } else if (campo.getType().equals(LocalDate.class)) {
                valor = LocalDate.parse(valor.toString());
            }

            ReflectionUtils.setField(campo, produto, valor);
        });

        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        repository.save(produto);
        return new ProdutoDetalhamentoDto(produto);
    }*/

    @Override
    @Transactional
    public ProdutoDetalhamentoDto atualizar(Long id, ProdutoDetalhamentoDto dto) {
        var categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(EntityNotFoundException::new);
        var produto = ProdutoFactory.update(dto);
        produto.setId(dto.id());
        produto.setCategoria(categoria);
        produto.setValor(dto.valor());
        produto.setDataCadastro(dto.dataCadastro());
        produto.setDataCadastro(LocalDateTime.now());
        repository.save(produto);
        return new ProdutoDetalhamentoDto(produto);

    }


}
