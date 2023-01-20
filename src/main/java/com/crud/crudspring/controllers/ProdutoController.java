package com.crud.crudspring.controllers;

import com.crud.crudspring.dto.DadosAtualizadosProduto;
import com.crud.crudspring.dto.DadosCadastroProduto;
import com.crud.crudspring.entities.Produto;
import com.crud.crudspring.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository){
        this.repository = produtoRepository;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<DadosAtualizadosProduto> cadastrar(@RequestBody DadosCadastroProduto dto, UriComponentsBuilder uriBuilder){
        var produto = new Produto(dto);
        repository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAtualizadosProduto(produto));
    }
}
