package com.ecopla.ecopla.controller;

import java.util.List;

import com.ecopla.ecopla.model.Produto;
import com.ecopla.ecopla.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/search")
    public List<Produto> search(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String cor) {
        return service.search(nome, minPrice, maxPrice, material, cor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto p) {
        Produto criado = service.criar(p);
        return ResponseEntity.status(201).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable String id, @Valid @RequestBody Produto p) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Produto atualizado = service.atualizar(id, p);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}