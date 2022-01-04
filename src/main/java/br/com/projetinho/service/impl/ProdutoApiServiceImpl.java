package br.com.projetinho.service.impl;

import br.com.projetinho.domain.ProdutosResponse;
import br.com.projetinho.domain.ClientResponse;
import br.com.projetinho.exception.ProdutosClientException;
import okhttp3.*;
import br.com.projetinho.config.ClientConfig;
import br.com.projetinho.service.ProdutoApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ProdutoApiServiceImpl implements ProdutoApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoApiServiceImpl.class);
    private final OkHttpClient httpClient;
    private final ClientConfig config;

    private ProdutoApiServiceImpl(ClientConfig config) {
        this.config = config;
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(this.config.getTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(this.config.getTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(this.config.getTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public void dispatch() {
        this.dispatch(response -> {
            if (response.getSuccess()) {
                LOG.info("[CLIENT] STATUS {} - OK", response.getStatusCode());
            } else {
                LOG.error("[CLIENT] Erro: {}", response.getMessage());
            }
        });
    }

    @Override
    public void dispatch(Consumer<ClientResponse> callback) {
        ClientResponse clientResponse = ClientResponse.Builder.create()
                .withMessage("Ok")
                .withStatusCode(200)
                .withSuccess(true)
                .build();

        ProdutosResponse produtosResponse = ProdutosResponse.Builder.create()
                .withData(new ArrayList<>())
                .withErros(new ArrayList<>())
                .build();

        try {
            Request httpRequest = new Request.Builder()
                    .url(this.config.getUrl())
                    .build();
            Call call = this.httpClient.newCall(httpRequest);
            Response response = call.execute();
            ResponseBody body = response.body();
            String bodyContent = body != null ? body.string() : "";
            this.throwIfError(response, bodyContent);
            produtosResponse = ProdutosResponse.fromJson(bodyContent);
            clientResponse.setStatusCode(response.code());
            clientResponse.setResponse(produtosResponse);
        } catch (IOException | ProdutosClientException e) {
            produtosResponse.getErros().add(e.getMessage());
            clientResponse.setMessage(e.getMessage());
            clientResponse.setSuccess(false);
            clientResponse.setResponse(produtosResponse);
            if (e instanceof ProdutosClientException) {
                clientResponse.setStatusCode(((ProdutosClientException) e).getStatusCode());
                if (!"".equals(((ProdutosClientException) e).getBodyContent())) {
                    LOG.error("[ClientException] - {}", ((ProdutosClientException) e).getBodyContent());
                }
            }
        }

        callback.accept(clientResponse);
    }

    private void throwIfError(Response response, String bodyContent) {
        if (response.code() != 200) {
            switch (response.code()) {
                case 204:
                    throw new ProdutosClientException("[ClientException] Lista retornada vazia!", response.code(), bodyContent);
                default:
                    throw new ProdutosClientException("[ClientException] Status [" + response.code() + "] retornado.", response.code(), bodyContent);
            }
        }
    }

    public static ProdutoApiService create(ClientConfig config) {
        return new ProdutoApiServiceImpl(config);
    }
}
