<h1 align="center">Título do Projeto: ConsumerWithSpringSecurityAPI</h1>

<p align="center">API Restful para consumo de uma api externa com cadastro de usuários sistema de priptografia de senha e segurança com JWT</p>

![Badge](https://img.shields.io/badge/WJBC-Sofwares-%237159c1?style=for-the-badge&logo=ghost)

<p align="center">
 <a href="#Objetivo">Objetivo</a> •
 <a href="#Features">Features</a> • 
 <a href="#Pré-Requisitos">Pré-Requisitos</a> •
 <a href="#Rodando-a-API">Rodando-a-API</a> • 
 <a href="#Tecnologias">Tecnologias</a> • 
 <a href="#MIT-License">MIT-License</a> • 
 <a href="#Autor-do-Projeto">Autor-do-Projeto</a>
</p>

<h4 align="center"> 
	🚧  API Restful 🚀 Concluída  🚧
</h4>

## Objetivo

Este projeto tem como objetivo a construção de uma API Restful para receber o cadastro de usuários que através de um sistema de login receberão um token JWT que lhes garantirá acesso aos endpoints de consulta de uma api externa que retornará listas e ojetos unicos do tipo Film, People e Planet, dentro da api serão convertidos para DTOs que retornarão ao usuário suas consultas, com isso é possível manipular o sistema de requisição de api de terceiros, a implementação de criptografia de senhas dos cadastros de usários, a utilização da converção de classes model para DTOs e a ativação do serviço de segurança por autenticação via token JWT.

## Features

- [X] Gerenciamento de usuários
- [X] Criptografia de senhas de usuários
- [X] Consumo de api terceira
- [X] Utilização de variáveis de ambiente
- [X] Implementação de segurança com SpringSecurity
- [X] Validação de requisições com token JWT


## Pré-Requisitos

Para a utilização desta API será necessário uma JRE Versão 11 ou superior no ambiente de produção, e o MySql Server instalado no seu servidor de banco de dados, por padrão a api está apontando na porta padrão(3306) do MySql para utilizar uma porta distinta faça a devida alteração.

## Rodando-a-API

```bash
# Clone este repositório
$ git clone <https://github.com/wellingtonpaim/ConsumerWithSpringSecurity>

# Acesse o projeto em sua maquina com sua ide e faça a adequação das variáveis de ambiente utilizadas para o acesso ao banco de dados, no arquivo application.properties, sendo a variavel da url, do username e da password, respectivamente: DB_URL, DB_USER, DB_KEY, configure estas variáveis com os valores correspondentes ao seu banco de dados, também será necessário configurar a variável de ambiente TOKEN_KEY que deverá carregar o valor de uma senha gerada automáticamente em base 64, está senha poderá ser gerada por exemplo no gerador online disponível na url <https://guidgenerator.com/online-guid-generator.aspx> . Caso seja necessário alterar a porta de acesso ao banco de dados faça esta alteração no valor que segue na url como por exemplo em: spring.datasource.url=jdbc:mysql://localhost:3306/consumerwhithspringsecurity. O arquivo application.properties fica em:
$ src/main/resources/application.properties

# O arquivo executavel desta API poderá ser gerado através da sua ide de preferencia, estando de posse do projeto em sua máquina. Tendo o arquivo executável do projeto, com o cmd ou terminal execute a aplicação com o comando:
$ java - jar nomegerado.jar

# O servidor inciará na porta 8080 - acesse: 
$ <http://localhost:8080>
```
## Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Java-Versão - 11](https://java.com/)
- [Spring Boot - 2.7.3](https://spring.io/projects/spring-boot/)
- [MySql - 8.0.30](https://mysql.com/)
- [Maven - 4.0.0](https://maven.apache.org)
- [Flyway - 8.5.13](https://flywaydb.org/)
- [Auth0 - 4.0.0](https://auth0.com/)
- [Modelmapper - 3.0.0](http://modelmapper.org/)
- [SpringSecurity - 5.7.3](https://spring.io/projects/spring-security)


## MIT-License

Copyright (c) <2022> <Wellington Paim>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Autor-do-Projeto


![Wellington Paim](https://github.com/wellingtonpaim/ConsumerWithSpringSecurity/blob/main/src/main/resources/static/fotoperfil.png)

<https://www.linkedin.com/in/paimwellington/>

<https://github.com/wellingtonpaim>