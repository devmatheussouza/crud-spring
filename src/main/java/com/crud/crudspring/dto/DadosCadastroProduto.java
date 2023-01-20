package com.crud.crudspring.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
        @NotNull
        String nome,
        @NotNull
        String marca,
        @NotNull
        String valor,
        @NotNull
        String url) {
}
