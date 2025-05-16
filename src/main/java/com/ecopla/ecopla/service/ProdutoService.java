package com.ecopla.ecopla.service;

import java.util.List;
import java.util.Optional;

import com.ecopla.ecopla.model.Produto;
import com.ecopla.ecopla.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public List<Produto> listarTodos() {
        return repo.findAll();
    }

    public Optional<Produto> buscarPorId(String id) {
        return repo.findById(id);
    }

    public Produto criar(Produto p) {
        p.setId(null);
        return repo.save(p);
    }

    public Produto atualizar(String id, Produto p) {
        p.setId(id);
        return repo.save(p);
    }

    public void deletar(String id) {
        repo.deleteById(id);
    }
}