package br.com.projetinho.exception;

public class ProdutosClientException extends RuntimeException{

    private int statusCode;
    private String bodyContent;

    public ProdutosClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ProdutosClientException(String message, int statusCode, String bodyContent) {
        this(message, statusCode);
        this.bodyContent = bodyContent;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    @Override
    public String toString() {
        return "ProdutosClientException{" +
                "statusCode=" + statusCode +
                ", bodyContent='" + bodyContent + '\'' +
                '}';
    }
}
