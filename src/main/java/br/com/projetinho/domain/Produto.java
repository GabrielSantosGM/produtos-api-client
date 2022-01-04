package br.com.projetinho.domain;

public class Produto {

    private Integer idProduto;
    private String nomeProduto;
    private Double valorProduto;
    private String tamanho;

    private Produto() {
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", valorProduto=" + valorProduto +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }

    public static final class Builder {
        private Integer idProduto;
        private String nomeProduto;
        private Double valorProduto;
        private String tamanho;

        private Builder() {
        }

        public static Builder aProduto() {
            return new Builder();
        }

        public Builder withIdProduto(Integer idProduto) {
            this.idProduto = idProduto;
            return this;
        }

        public Builder withNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
            return this;
        }

        public Builder withValorProduto(Double valorProduto) {
            this.valorProduto = valorProduto;
            return this;
        }

        public Builder withTamanho(String tamanho) {
            this.tamanho = tamanho;
            return this;
        }

        public Produto build() {
            Produto produto = new Produto();
            produto.setIdProduto(idProduto);
            produto.setNomeProduto(nomeProduto);
            produto.setValorProduto(valorProduto);
            produto.setTamanho(tamanho);
            return produto;
        }
    }
}
