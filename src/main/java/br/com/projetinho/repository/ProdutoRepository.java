package br.com.projetinho.repository;

import br.com.projetinho.domain.Produto;

import java.util.List;

public interface ProdutoRepository {

    void saveAll(List<Produto> values);

    Produto get(String name);

    List<Produto> getAll();
}
