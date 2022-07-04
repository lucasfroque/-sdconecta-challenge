<h1 align="center" id="title">Teste de backend do SD Conecta</h1>

<p align="center"><img src="https://socialify.git.ci/lucasfroque/sdconecta-challenge/image?language=1&amp;name=1&amp;owner=1&amp;pattern=Solid&amp;theme=Dark" alt="project-image"></p>

## Índice
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Como usar](#como-usar)
- [Como autenticar](#como-autenticar)
- [Documentação da API](#documentação-da-api)
- [Rodando testes](#rodando-os-testes)

## 🛠 Tecnologias utilizadas

- Java 17
- Maven
- Springboot
- SpringSecurity
- Spring Data JPA
- H2 Database
- MySQL
- Flyway
- Junit/Mockito

## Como usar

### Clone o projeto

```bash
  git clone https://github.com/lucasfroque/sdconecta-challenge
```
### Instale as dependências

```bash
  mvn clean install
```
### Configure o src/main/resources/application.properties
```yaml
spring.profiles.active=test
spring.jpa.open-in-view=true

env.tokenUrl= https://beta.sdconecta.com/oauth2/token
env.generateTokenUrl= https://beta.sdconecta.com/api/v2/partners/generate-user-token
env.clientId=
env.clientSecret=
```
- NECESSÁRIO COLOCAR CLIENTID E CLIENTSECRET
- Caso queira usar o banco de dados H2 use o profile "test" (**É NECESSARIO REMOVER O FLYWAY DO POM.XML**)
- Caso queira usar o banco de dados MySQL use o profile "dev" e configure sua datasource.url em src/main/resources/application-dev.properties

### Inicie o servidor

```bash
  mvn spring-boot:run
```

### IMPORTANTE
Este projeto utiliza autenticação basica(BASE64), é necessario autenticação em toda requisição(*EMAIL* e *SENHA*).

Somente usuário com perfil admin poder Cadastrar, Alterar e Excluir usuários e crms;

Independete se estiver usando H2 ou MySQL será criado automaticamente os usuários e suas crms.

- USUÁRIO 1: 
  email: usuario.teste-1@email.com | senha: lcs | role: ADMIN |
- USUÁRIO 2: 
  email: usuario.teste-2@email.com | senha: lcs | role: USER | 
- USUÁRIO 3:
  email: joao@email.com | senha: lcs | role: USER |

## Como autenticar

Envie uma requisição com a key "Authorization" com o valor "Basic email:senha(Codificado em base64)" no header

Ou use Baisc Auth do Postman passando o email e a senha

## Documentação da API

## USER

#### Retorna todos usuários(podendo filtrar por nome ou especialidade)
```http
  GET api/v1/users
```
| Filtros   | Tipo       |
| :---------- | :--------- | 
| `name`      | `opcional` | 
| `specialty`      | `opcional` | 


#### Retorna um usuário pelo id

```http
  GET api/v1/users/{id}
```

#### Edita um usuário

```http
  PUT api/v1/users/{id}
```
#### Deleta um usuário (Para deletar um usuário você deverá deletar suas crms previamente)

```http
  PUT api/v1/users/{id}
```

#### Cria um novo usuário

```http
  POST api/v1/users
```

| Parâmetro   | Tipo       |
| :---------- | :--------- | 
| `email`      | `string` | 
| `password`      | `string` | 
| `name`      | `string` |
| `surname`      | `String` | 
| `mobilePhone`      | `String` | 

## CRM

#### Retorna todas as crms
```http
  GET api/v1/crms
```

#### Retorna uma crm pelo id

```http
  GET api/v1/crms/{id}
```

#### Edita uma crm

```http
  PUT api/v1/crms/{id}
```
#### Deleta uma crm

```http
  PUT api/v1/crms/{id}
```

#### Cria um nova crm

```http
  POST /api/v1/users/{userId}/crms
```

| Parâmetro   | Tipo       |
| :---------- | :--------- | 
| `crm`      | `string` | 
| `uf`      | `string` | 
| `specialty`      | `string` |

## Login

tenta autenticar o usuário no SD Conecta usando o email que você passou para autenticar nesta API.

```http
  GET /login
```

## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn test
```

  
