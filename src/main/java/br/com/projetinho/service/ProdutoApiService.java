package br.com.projetinho.service;

import br.com.projetinho.domain.ClientResponse;

import java.util.function.Consumer;

public interface ProdutoApiService {

    void dispatch();

    void dispatch(Consumer<ClientResponse> callback);
}
