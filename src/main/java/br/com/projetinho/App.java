package br.com.projetinho;

import br.com.projetinho.config.ClientConfig;
import br.com.projetinho.service.ProdutoService;
import br.com.projetinho.service.impl.ProdutoServiceImpl;

import java.util.concurrent.TimeoutException;

public class App {

    public static void main(String[] args) throws TimeoutException {
        ClientConfig config = createConfig();
        ProdutoService produtoService = createService(config);

        if (produtoService.untilLoaded()) {
            System.out.println(produtoService.containProduct("hokage"));
        }
    }

    private static ClientConfig createConfig() {
        return ClientConfig.Builder.create()
                .withUrl("http://localhost:8080/produtos")
                .withPeriodo(500L)
                .withTimeout(10000)
                .build();
    }

    private static ProdutoService createService(ClientConfig config) {
        ProdutoService produtoService = new ProdutoServiceImpl(config);
        produtoService.start();
        return produtoService;
    }
}
