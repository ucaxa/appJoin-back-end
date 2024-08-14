package com.join.testejoin.datasource.entity;


import com.join.testejoin.model.categoria.CategoriaDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Table
@Entity(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "categoria", fetch = LAZY)
    private List<Produto> produtos;

    public Categoria(CategoriaDto dto) {
        this.nome = dto.nome();
    }
}
