package br.com.projetinho.service;

import java.util.concurrent.TimeoutException;

public interface ProdutoService {

    void start();

    void stop();

    boolean isLoaded();

    boolean untilLoaded() throws TimeoutException;

    boolean containProduct(String name);
}
