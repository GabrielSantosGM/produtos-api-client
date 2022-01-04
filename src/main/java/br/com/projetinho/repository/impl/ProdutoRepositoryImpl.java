package br.com.projetinho.repository.impl;

import br.com.projetinho.domain.Produto;
import br.com.projetinho.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProdutoRepositoryImpl implements ProdutoRepository {

    private static ProdutoRepositoryImpl uniqueInstance;
    private List<Produto> source;

    private ProdutoRepositoryImpl() {
    }

    @Override
    public void saveAll(List<Produto> values) {
        this.source = new ArrayList<>();
        source.addAll(values);
    }

    @Override
    public Produto get(String name) {
        return source.stream()
                .filter(produto -> produto.getNomeProduto().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("Nenhum produto com o nome [" + name + "] encontrado.");
                });
    }

    @Override
    public List<Produto> getAll() {
        return new ArrayList<>(source);
    }

    public static ProdutoRepository getInstance() {
        if (uniqueInstance == null) {
            return new ProdutoRepositoryImpl();
        }
        return uniqueInstance;
    }
}
