
# Authentication API - Desafio BackendBr
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/> <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens"/>

Resolução do desafio proposto pelo repositório Backend Brasil. Mais detalhes [aqui.](https://github.com/backend-br/desafios/blob/master/authentication/PROBLEM.md).

Este projeto é uma API construída com Java, Spring, Banco de dados com PostgresSQL, Flyway Migrations, e Spring Security para controle de autenticação.


## Instalação 
1. Clone o repositório:
```bash
git@github.com:dayvidj/auth-api.git
```
2. Instale dependências com Maven
3. Instale o PostgreSQL 

Após iniciar a aplicação a API estará acessível em [http://localhost:8080](http://localhost:8080)

## API Endpoints
```bash 
POST /auth/login: Realiza a autenticação de um usuário e retorna um token JWT válido para acesso das demais requisições.
POST /auth/register: Registra um novo usuário no banco de dados

GET /auth/foo-bar: Ao enviar no header Authorization um token válido retorna um HTTP Status code 204 NoContent,
caso contrárío, retorna HTTP Status 401 Unauthorized com a mensagem específicando o problema.
```



