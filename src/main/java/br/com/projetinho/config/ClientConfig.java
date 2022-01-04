package br.com.projetinho.config;

public class ClientConfig {

    private String url;
    private long periodo;
    private int timeout;

    private ClientConfig() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(long periodo) {
        this.periodo = periodo;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public static final class Builder {
        private String url;
        private long periodo;
        private int timeout;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withPeriodo(long periodo) {
            this.periodo = periodo;
            return this;
        }

        public Builder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public ClientConfig build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setUrl(url);
            clientConfig.setPeriodo(periodo);
            clientConfig.setTimeout(timeout);
            return clientConfig;
        }
    }
}
