package com.crud.crudspring.dto;

import com.crud.crudspring.entities.Produto;

public record DadosAtualizadosProduto(Long id, String nome, String marca, String valor, String url) {
    public DadosAtualizadosProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getMarca(), produto.getValor(), produto.getUrl());
    }
}
