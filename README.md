# coopervoto
API Rest para gerenciar sessões de votação.


O serviço permite:
- Cadastrar Associado
- Cadastrar Pauta para votação
- Consultar Pauta por ID
- Listar Pautas que estão com sessão aberta (recebendo voto)
- Iniciar Sessão de votação
- Votar nas pautas que estão com sessão aberta
- Obter resultado da votação


## Tecnologias Utilizadas

- Java 11
- IDE Spring STS
- Gradle para gerenciar as dependências
- Spring Boot, Starters Web/Test, Data, Actuator (métricas), DevTools, JUnit5, Mockito, Swagger
- MongoDB banco de dados NoSQL baseado em documentos
- Postman para realizar requisições de teste na api
- Docker
- Git


## Arquitetura

Dividida em 3 camadas


## Execução

```shell
docker-compose up
./gradlew bootRun

```

**Exemplos de requisição:**

Cadastro de Associado
```shell
curl --location --request POST 'http://localhost:8080/api/v1/associado' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cpf": "35327189015",
    "nome": "Maria dos Santos"
}'
```

Cadastro de Pauta
```shell
curl --location --request POST 'http://localhost:8080/api/v1/pauta' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Vacina",
    "descricao": "Ofertar gratuitamente a Vacina."
}'
```

Iniciar Sessão de votação em uma Pauta
```shell
curl --location --request POST 'http://localhost:8080/api/v1/iniciarsessao' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pauta": "6006fc239a790046303645fb",
    "duracao": 5
}'
```

Listar Pautas disponíveis para votação
```shell
curl --location --request GET 'http://localhost:8080/api/v1/pautas'
```

Votar
```shell
curl --location --request POST 'http://localhost:8080/api/v1/votar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pauta": "6006fc239a790046303645fb",
    "cpf": "35327189015",
    "opcao": "SIM"
}'
```

Obter resultado da votação em uma pauta
```shell
curl --location --request GET 'http://localhost:8080/api/v1/resultado/6006fc239a790046303645fb' 
```


## Extras

Foi disponibilizado um endpoint para extração de métricas: 
http://localhost:8080/actuator

A documentação da API foi feita com Swagger e pode ser acessada em: 
http://localhost:8080/swagger-ui/index.html