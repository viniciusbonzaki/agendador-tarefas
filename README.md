# API de Tarefas — Agendador de Tarefas

## 📌 Sobre o Projeto

O Agendador de Tarefas tem como objetivo permitir que usuários cadastrem tarefas com datas e horários específicos e recebam notificações por e-mail quando o momento programado se aproximar.

Para organizar as responsabilidades da aplicação, o sistema foi desenvolvido com base na **arquitetura de microserviços**, dividindo suas funcionalidades em serviços independentes e especializados.

O ecossistema é composto por quatro microserviços:

- **API de Usuários:** responsável pelo cadastro, consulta, atualização e exclusão de usuários, incluindo o gerenciamento de seus endereços e telefones.
- **API de Tarefas:** responsável pelo gerenciamento das tarefas cadastradas na aplicação.
- **API de Notificação:** responsável pelo processamento e envio de notificações por e-mail.
- **BFF (Backend for Frontend):** responsável por integrar os demais microserviços e centralizar a comunicação com a camada cliente.

Este repositório é dedicado exclusivamente à **API de Tarefas**, um dos quatro microserviços que compõem o Agendador de Tarefas.

Desenvolvida com **Java 17 e Spring Boot**, a API é responsável pelo gerenciamento das tarefas relacionadas aos usuários, disponibilizando recursos para criação, consulta, atualização, exclusão e alteração do status das tarefas.

O microserviço também utiliza **Gradle** para gerenciamento de dependências e build, **Docker** para containerização da aplicação e **Spring Cloud OpenFeign** para comunicação com a API de Usuários.

## 🎯 Funcionalidades

A API de Tarefas disponibiliza as seguintes funcionalidades:

### Gerenciamento de tarefas

- Cadastro de novas tarefas.
- Consulta de tarefas cadastradas na aplicação.
- Atualização das informações de uma tarefa existente.
- Exclusão de uma tarefa a partir de seu identificador.
- Consulta de tarefas associadas a um usuário por meio de seu e-mail.

### Consulta de eventos por período

- Consulta de tarefas dentro de um intervalo de datas.
- Utilização de uma data inicial e uma data final para delimitar o período da consulta.
  
### Gerenciamento de status

- Atualização do status de uma tarefa específica.
- Separação da operação de alteração de status das demais operações de atualização, utilizando o método HTTP `PATCH`.

### Integração entre microserviços

- Comunicação com a API de Usuários por meio do Spring Cloud OpenFeign.
- Integração com os recursos necessários para relacionar tarefas aos usuários cadastrados.
- Organização das responsabilidades de gerenciamento de tarefas de forma independente dos demais microserviços.

## 🛠️ Tecnologias Utilizadas

As principais tecnologias e ferramentas utilizadas no desenvolvimento deste microserviço são:

- **Java 17:** linguagem de programação utilizada no desenvolvimento.
- **Spring Boot:** framework para desenvolvimento da aplicação backend e exposição dos recursos REST.
- **Gradle:** ferramenta de gerenciamento de dependências e automação do build.
- **Docker:** ferramenta utilizada para containerização da aplicação.
- **Spring Cloud OpenFeign:** recurso utilizado para facilitar a comunicação HTTP entre microserviços.
- **Spring Security:** utilizado na configuração de segurança da aplicação, por meio das classes presentes na camada de infraestrutura.

## 📂 Arquitetura e Organização do Projeto

O projeto utiliza uma organização em camadas para separar as responsabilidades da aplicação, facilitar a manutenção do código e promover a separação entre regras de negócio, infraestrutura e exposição dos endpoints.

### Business

Camada responsável por concentrar a lógica de negócio e os componentes utilizados no processamento das operações relacionadas às tarefas.

- `TarefaService`: implementa as regras de negócio relacionadas ao cadastro, consulta, atualização, exclusão e alteração de status das tarefas.
- `DTOs`: objetos utilizados para transportar dados entre as camadas e nas operações da API.
- `Converters`: componentes responsáveis pela conversão entre DTOs e entidades, facilitando a comunicação entre as diferentes camadas da aplicação.

### Controller

Camada responsável por expor os recursos HTTP e receber as requisições dos clientes.

- `TarefaController`: disponibiliza os endpoints relacionados ao gerenciamento de tarefas, incluindo operações de cadastro, consulta, atualização, exclusão e alteração de status.
- `GlobalExceptionHandler`: centraliza o tratamento de exceções e a construção das respostas de erro da API.

### Infrastructure

Camada responsável pelos componentes de persistência, infraestrutura técnica, segurança e comunicação com outros serviços.

- `Entity`: representa a entidade persistida no banco de dados.
- `Exceptions`: agrupam as exceções específicas utilizadas pela aplicação.
- `Repository`: abstrai as operações de acesso e persistência dos dados das tarefas.
- `Security`: contém as classes responsáveis pelas configurações de segurança do microserviço.
- `Client`: contém o Feign Cliente necessário para a comunicação com a API de Usuários.

## 🗃️ Modelagem de Dados

A principal entidade do domínio deste microserviço é:

- **Tarefa:** representa uma tarefa cadastrada na aplicação e armazena os atributos necessários para seu gerenciamento, incluindo as informações utilizadas nas operações de consulta, atualização, exclusão e controle de status.

A entidade `Tarefa` constitui a única entidade do microserviço, mantendo a responsabilidade pelo gerenciamento dos dados relacionados às tarefas concentrada neste serviço.

A associação das tarefas aos usuários é realizada por meio das informações utilizadas na integração com a API de Usuários, de acordo com a implementação do microserviço.

## 🔐 Autenticação e Segurança

A aplicação utiliza **Spring Security** em conjunto com **JSON Web Token (JWT)** para implementar o mecanismo de autenticação.

Essa abordagem permite autenticar usuários e validar tokens nas requisições protegidas, de acordo com as regras configuradas para a aplicação.

- **`JwtRequestFilter`:** intercepta requisições para processar e validar o token JWT, conforme a implementação.
- **`JwtUtil`:** centraliza operações relacionadas à validação de tokens JWT.
- **`SecurityConfig`:** define as configurações de segurança e as regras de acesso aos endpoints.
- **`UserDetailsServiceImpl`:** implementa o carregamento dos dados do usuário para integração com o Spring Security.

Essa abordagem permite que o microserviço valide as credenciais presentes nas requisições sem assumir a responsabilidade pela autenticação centralizada e pela emissão de tokens, mantendo a separação de responsabilidades entre os serviços da arquitetura.

## ⚠️ Tratamento de Exceções

O projeto possui uma classe `GlobalExceptionHandler`, responsável por centralizar o tratamento das exceções geradas durante o processamento das requisições.

Essa abordagem permite separar o tratamento de falhas da lógica dos controladores e padronizar as respostas de erro disponibilizadas pela API.

As exceções específicas utilizadas pelo microserviço estão organizadas na camada `Infrastructure`, facilitando a identificação e o tratamento das situações excepcionais relacionadas às operações de gerenciamento de tarefas.

## 🌐 Endpoints da API

Esta seção apresenta as operações disponibilizadas pela API de Tarefas.

Os caminhos exatos das rotas, os parâmetros e os formatos das requisições devem ser confirmados na implementação do `TarefaController`.

| Método HTTP | Recurso | Descrição |
|---|---|---|
| `POST` | Tarefas | Cadastrar uma nova tarefa. |
| `GET` | Eventos | Consultar tarefas em um período. |
| `GET` | Tarefas por usuário | Buscar as tarefas associadas a um usuário por meio de seu e-mail. |
| `DELETE` | Tarefas | Excluir uma tarefa a partir de seu ID. |
| `PUT` | Tarefas | Atualizar as informações de uma tarefa existente. |
| `PATCH` | Status da tarefa | Atualizar o status de uma tarefa específica. |

### Exemplos de utilização

Os exemplos completos de requisições podem ser documentados com os seguintes dados:

- **Cadastro de tarefa:** JSON com as informações exigidas para registrar uma nova tarefa.
- **Consulta de eventos:** parâmetros correspondentes à data inicial e à data final do período desejado.
- **Consulta por usuário:** e-mail utilizado para identificar o usuário cujas tarefas serão consultadas.
- **Exclusão de tarefa:** identificador da tarefa que será removida.
- **Atualização de tarefa:** identificador e corpo da requisição contendo as informações a serem atualizadas.
- **Atualização de status:** identificador da tarefa e os dados necessários para definir seu novo status.

Para cada endpoint, recomenda-se documentar a rota exata, os parâmetros, o corpo JSON, os códigos HTTP de sucesso e os possíveis erros.

## 🔗 Integração com a API de Usuários

A API de Tarefas utiliza o **Spring Cloud OpenFeign** para realizar a comunicação com a API de Usuários.

Essa integração permite que o microserviço consulte informações disponibilizadas pelo serviço responsável pelo gerenciamento dos usuários, mantendo a separação de responsabilidades entre os componentes da arquitetura.

O fluxo geral de integração contempla:

1. Recebimento de uma requisição relacionada a uma tarefa.
2. Identificação das informações do usuário necessárias para a operação.
3. Comunicação com a API de Usuários por meio do Feign Client, quando aplicável.
4. Processamento da resposta recebida.
5. Continuidade da operação conforme as regras de negócio implementadas.

A utilização do Feign Client facilita a comunicação entre os microserviços e mantém os detalhes das chamadas HTTP organizados em um componente específico da camada de infraestrutura.

## 🐳 Docker

O projeto utiliza Docker para containerização, facilitando a padronização do ambiente de execução e a implantação da aplicação.

As configurações de execução estão presentes no arquivo `Dockerfile`.

Antes de executar a aplicação, verifique as configurações de ambiente e os parâmetros necessários para a comunicação entre os microserviços.

## 📚 Conceitos Aplicados

O desenvolvimento deste microserviço envolve conceitos relevantes para a construção de aplicações backend:

- Arquitetura de microserviços.
- Desenvolvimento de APIs REST.
- Separação de responsabilidades em camadas.
- Programação com Java 17 e Spring Boot.
- Implementação de regras de negócio com uma camada de serviço.
- Utilização de DTOs e Mappers/Converters.
- Persistência e acesso a dados por meio de entidades e repositórios.
- Comunicação entre microserviços com Spring Cloud OpenFeign.
- Configuração de segurança com Spring Security.
- Tratamento global de exceções.
- Containerização com Docker.
- Gerenciamento de dependências e build com Gradle.
