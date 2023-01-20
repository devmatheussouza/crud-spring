package com.crud.crudspring.controllers;

import com.crud.crudspring.dto.DadosAtualizacao;
import com.crud.crudspring.dto.DadosAtualizadosProduto;
import com.crud.crudspring.dto.DadosCadastroProduto;
import com.crud.crudspring.entities.Produto;
import com.crud.crudspring.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<DadosAtualizadosProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dto, UriComponentsBuilder uriBuilder){
        var produto = new Produto(dto);
        repository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAtualizadosProduto(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosAtualizadosProduto> buscarProdutoPorId(@PathVariable Long id){
        try{
            var produto = repository.getReferenceById(id);
            return ResponseEntity.ok(new DadosAtualizadosProduto(produto));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Page<DadosAtualizadosProduto>> listarTodosProdutos(@PageableDefault(sort = {"valor"}, direction = Sort.Direction.DESC) Pageable page){
        var pageRequest = repository.findAll(page).map(DadosAtualizadosProduto::new);
        return ResponseEntity.ok(pageRequest);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosAtualizadosProduto> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacao dto){
        try{
            var produto = repository.getReferenceById(id);
            produto.atulizarProduto(dto);
            return ResponseEntity.ok(new DadosAtualizadosProduto(produto));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        try{
            var produto = repository.getReferenceById(id);
            repository.delete(produto);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
