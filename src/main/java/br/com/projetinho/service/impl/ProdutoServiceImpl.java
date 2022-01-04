package br.com.projetinho.service.impl;

import br.com.projetinho.config.ClientConfig;
import br.com.projetinho.repository.ProdutoRepository;
import br.com.projetinho.repository.impl.ProdutoRepositoryImpl;
import br.com.projetinho.service.ProdutoApiService;
import br.com.projetinho.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProdutoServiceImpl implements ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    private final ClientConfig config;
    private final ProdutoApiService produtoApiService;
    private final ProdutoRepository produtoRepository;
    private final ScheduledExecutorService runnable;
    private boolean loaded;

    public ProdutoServiceImpl(ClientConfig config) {
        this.config = config;
        this.produtoApiService = ProdutoApiServiceImpl.create(config);
        this.produtoRepository = ProdutoRepositoryImpl.getInstance();
        this.runnable = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void start() {
        this.runnable.scheduleAtFixedRate(() -> this.produtoApiService.dispatch(response -> {
            if (response.getSuccess()) {
                produtoRepository.saveAll(response.getResponse().getData());
                loaded = true;
            }
        }), 0, config.getPeriodo(), TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        this.runnable.shutdown();
    }

    @Override
    public boolean isLoaded() {
        return this.loaded;
    }

    @Override
    public boolean untilLoaded() throws TimeoutException {
        long start = System.currentTimeMillis();
        while (!this.isLoaded()) {
            try {
                if ((System.currentTimeMillis() - start) >= config.getTimeout()) {
                    throw new TimeoutException("Tempo para o carregamento dos produtos excedido!");
                }
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                LOG.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    @Override
    public boolean containProduct(String name) {
        return this.produtoRepository
                .getAll()
                .stream()
                .anyMatch(produto -> produto.getNomeProduto().toLowerCase().contains(name.toLowerCase()));
    }
}
