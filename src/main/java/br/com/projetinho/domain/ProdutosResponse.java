package br.com.projetinho.domain;

import com.google.gson.Gson;

import java.util.List;

public class ProdutosResponse {

    private List<Produto> data;
    private List<String> erros;

    private ProdutosResponse() {
    }

    public static ProdutosResponse fromJson(String bodyContent){
        return new Gson().fromJson(bodyContent, ProdutosResponse.class);
    }

    public List<Produto> getData() {
        return data;
    }

    public void setData(List<Produto> data) {
        this.data = data;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "ProdutosResponse{" +
                "data=" + data +
                ", erros=" + erros +
                '}';
    }

    public static final class Builder {
        private List<Produto> data;
        private List<String> erros;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withData(List<Produto> data) {
            this.data = data;
            return this;
        }

        public Builder withErros(List<String> erros) {
            this.erros = erros;
            return this;
        }

        public ProdutosResponse build() {
            ProdutosResponse dataProdutoResponse = new ProdutosResponse();
            dataProdutoResponse.setData(data);
            dataProdutoResponse.setErros(erros);
            return dataProdutoResponse;
        }
    }
}
