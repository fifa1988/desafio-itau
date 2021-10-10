## API Desafio Itaú

Esta API foi desenvolvida utilizando Java, pois tenho bastante conhecimento sobre o framework spring e JUnit. Eu poderia ter desenvolvido com Python utilizando flask, contudo não tenho experiência com testes unitários em python, logo eu iria levar mais tempo para desenvolver essa API. 

# Requirements

Para buildar e rodar você precisa:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6.3](https://maven.apache.org)

# Rodando a aplicação localmente

Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método `main` na classe `br.com.itau.desafio.DesafioItauApiApplication` de seu IDE.

Alternativamente, você pode usar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

# Documentação

Para acessar a documentação localhost acesse o link: http://localhost:5000/swagger-ui.html#/

Para gerar o token chame o endpoint http://localhost:5000/oauth/token?grant_type=password&username=EMAILUSER&password=SENHAUSER. Na pasta src/main/resource estou disponibilizando o arquivo Geral.postman_collection.json que pode ser importado no POSTMAN, o mesmo irá ajudar você a ta se autenticando e com o token vai poder ta consumindo os serviços descritos abaixo.

curl -X GET "http://localhost:5000/todo?status=pedding" -H "accept: application/json"

curl -X POST "http://localhost:5000/todo" -H "Authorization: Bearer Token" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"descricao\": \"Criação de nova funcionalidade\", \"resumo\": \"Após todos os passos mandar um email com proposta para contratar o dev :P\", \"status\": \"pedding\", \"dataCadastro\": \"2021-10-09T15:57:10\", \"dataAtualizacao\": null}"

curl -X PUT "http://localhost:5000/todo/3/completed" -H "Authorization: Bearer Token" -H "accept: application/json"


Para verificar o status da aplicação localhost acesse o link: http://localhost:5000/actuator/health

Para realizar o monitoramento geral da aplicação localhost acesse o link: http://localhost:5000/actuator


# Outras informações

Estou usando o banco em memória h2 - http://localhost:5000/h2-console

O projeto também está pronto para utilizar o Flyway para controlá versionamento de script sql; A criação do banco de dados e os inserts iniciais já estão sendo feitos de forma automática.

Há testes unitários criados e ao empacotar o projeto os mesmos são execudos de forma automática. Contudo os mesmos não estão com uma cobertura de 80%.
	

# Direito autoral

Lançado sob o Apache License 2.0. Ver o [LICENSE](https://github.com/fifa1988/desafio-itau/blob/main/LICENSE) arquivo.

	