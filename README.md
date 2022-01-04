# produtos-api-client
Biblioteca de consumo de uma API Rest básica de produtos disponibilizada no repositório: <a href="https://github.com/GabrielSantosGM/produtos-api-service">clique aqui para acessar</a>  

Com essa biblioteca, estou consumindo os dados do endpoint que recupera uma lista com todos os produtos cadastrados no banco de dados (que está alocado na nuvem do Azure) e carrega um cachê _singleton_ local, fazendo assim com que não tenha necessidade de sempre que precisar de uma informação do produto, faça a requisição novamente no endpoint. Vale ressaltar que o processo ocorre de maneira automática com um periodo de tempo em segundos, passado como parâmetro na instância do objeto *ClientConfig*. 

Para utilizar a biblioteca, é necessário criar uma instancia para a configuração da conexão: 
```
private static ClientConfig createConfig() {
        return ClientConfig.Builder.create()
                .withUrl("http://localhost:8080/produtos")
                .withPeriodo(500L)
                .withTimeout(10000)
                .build();
    }
```

A seguir, criamos o nosso serviço que cuidará de iniciar todo o sistema e carregar o cache com os produtos vindos da API:
```
private static ProdutoService createService(ClientConfig config) {
        ProdutoService produtoService = new ProdutoServiceImpl(config);
        produtoService.start();
        return produtoService;
    }
```

Feito isso, a biblioteca poderá ser utilizada na sua aplicação. Exemplo feito em uma classe main *App.java* disponibilizada na biblioteca também:
```
public static void main(String[] args) throws TimeoutException {
        ClientConfig config = createConfig();
        ProdutoService produtoService = createService(config);

        if (produtoService.untilLoaded()) {
            System.out.println(produtoService.containProduct("hokage"));
        }
    }
```  
#### OBS: O método *containProduct(String name)* foi desenvolvido para fins de demonstração, qualquer implementação de métodos mais conveninentes para o seu contexto também seriam válidos na classe de serviço.
