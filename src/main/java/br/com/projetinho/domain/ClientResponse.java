package br.com.projetinho.domain;

public class ClientResponse {

    private String message;
    private int statusCode;
    private boolean success;
    private ProdutosResponse response;

    private ClientResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ProdutosResponse getResponse() {
        return response;
    }

    public void setResponse(ProdutosResponse response) {
        this.response = response;
    }

    public static final class Builder {
        private String message;
        private int statusCode;
        private boolean success;
        private ProdutosResponse response;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder withResponse(ProdutosResponse response) {
            this.response = response;
            return this;
        }

        public ClientResponse build() {
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setMessage(message);
            clientResponse.setStatusCode(statusCode);
            clientResponse.setSuccess(success);
            clientResponse.setResponse(response);
            return clientResponse;
        }
    }
}
