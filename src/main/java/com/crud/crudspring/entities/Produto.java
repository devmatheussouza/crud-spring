package com.crud.crudspring.entities;

import com.crud.crudspring.dto.DadosCadastroProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Produto")
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String marca;
    private String valor;
    private String url;

    public Produto(DadosCadastroProduto dados){
        this.nome = dados.nome();
        this.marca = dados.marca();
        this.valor = dados.valor();
        this.url = dados.url();
    }
}
