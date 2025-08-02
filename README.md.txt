# Projeto Consulta de CEP via API

Este projeto implementa uma API que consulta informações de endereço a partir de um CEP, consumindo a API pública ViaCEP e também um mock local para testes.

## Tecnologias usadas

- Java 21 (o projeto foi configurado com Java 21 via Spring Initializr, embora a máquina local tenha Java 22 instalado)
- Spring Boot
- Gradle (gerenciador de dependências e build)
- JPA/Hibernate
- PostgreSQL
- Mockoon (mock de API)
- Postman (teste de API)

## Como funciona

- Foi criada uma entidade JPA `Registro` que representa os dados salvos no banco PostgreSQL.
- O projeto segue o padrão MVC, com repositórios, serviços e controllers para organizar as responsabilidades.
- O serviço consome a API ViaCEP usando `RestTemplate` para fazer requisições HTTP.
- Para receber os dados da ViaCEP, foi criada uma classe DTO que mapeia a resposta da API (não persistida no banco).
- A aplicação trata os principais status HTTP, como 200 (OK), 404 (Not Found) e erros internos.
- O Spring gerencia o `RestTemplate` via configuração em `AppConfig`.
- A configuração do JPA com `entityScan` garante que as entidades sejam corretamente mapeadas para criação das tabelas no banco.
- Para testes locais e simulação da API, foi usado o Mockoon, configurado com um ambiente mock que retorna um JSON fictício para um CEP específico.
- No serviço, foi fácil alternar a base URL da API entre a ViaCEP oficial e o mock local para testes.
- Testes foram realizados no Postman, validando tanto a API real quanto o mock, com sucesso.

## Como rodar

1. Configure o banco PostgreSQL e ajuste as credenciais em `application.properties`.
2. Execute a aplicação Spring Boot usando Gradle, que criará as tabelas automaticamente:

```bash
./gradlew bootRun
