# WFgeren - Catálogo de Itens de Jogo com Inventário de Usuário

## Visão geral
WFgeren é uma API REST (Spring Boot) para cadastrar, catalogar e gerenciar itens de um jogo. Cada item possui nome, valores monetários (Platina e Dukat), tipo de equipamento e pode pertencer a um conjunto (ex.: partes de uma arma). Itens são organizados em categorias; conjuntos reúnem itens; cada usuário tem um inventário pessoal para registrar seus itens.

Principais entidades: Usuario, Inventario, Conjunto, Itens, Categoria. Relacionamentos principais (resumo):
- Usuario 1:1 Inventario
- Inventario N:N Conjunto (inventário contém conjuntos)
- Conjunto 1:N Itens
- Conjunto N:1 Categoria

## Recursos (endpoints principais)
Observação: caminhos exatos podem variar conforme os controllers do projeto; abaixo estão os padrões usados pela API:

- Autenticação
  - POST /api/auth/login — autentica e retorna JWT
  - POST /api/auth/register — cria novo usuário (se implementado)

- Usuário
  - GET /api/users — listar usuários
  - GET /api/users/{id} — obter usuário por id
  - POST /api/users — criar usuário
  - PUT /api/users/{id} — atualizar usuário
  - DELETE /api/users/{id} — remover usuário

- Categoria
  - GET /api/categorias
  - GET /api/categorias/{id}
  - POST /api/categorias
  - PUT /api/categorias/{id}
  - DELETE /api/categorias/{id}

- Conjunto
  - GET /api/conjuntos
  - GET /api/conjuntos/{id}
  - POST /api/conjuntos
  - PUT /api/conjuntos/{id}
  - DELETE /api/conjuntos/{id}

- Itens
  - GET /api/itens
  - GET /api/itens/{id}
  - POST /api/itens
  - PUT /api/itens/{id}
  - DELETE /api/itens/{id}

- Inventário
  - GET /api/inventarios/{usuarioId} — inventário de um usuário
  - POST /api/inventarios/{usuarioId}/itens — adicionar item ao inventário
  - DELETE /api/inventarios/{usuarioId}/itens/{itemId} — remover item

Exemplos de query params (a implementar se necessário):
- GET /api/itens?categoria=Armas&sort=platina,desc
- GET /api/itens?minPlatina=10&maxDukat=500

## Arquitetura e padrões aplicados
- Spring Boot (API REST)
- Camadas: Controllers, Services, Repositories
- Persistência: Spring Data JPA (JpaRepository)
- DTOs: entrada/saída via classes em `DTO/`
- Validação: Bean Validation nas DTOs (anotações como @NotBlank, @Positive)
- Tratamento de exceções: `GlobalExceptionHandler` com @ControllerAdvice
- Autenticação: JWT (filtros e serviços em `Config/`) — endpoints de login / emissão de token
- Documentação OpenAPI/Swagger: configuração presente em `Config/OpenAPIConfig.java` (é necessário adicionar a dependência do springdoc no pom.xml)

## Pré-requisitos
- Java 17+
- Maven 3.8+
- Banco de dados configurado em `src/main/resources/application.yml` (H2, PostgreSQL, MySQL, etc.)

## Como executar (Windows)
1. Abra o terminal na pasta do projeto:
   cd "c:\Users\paulo\OneDrive\Documentos\Sistems corporativos\projeto final\WFgeren-1"

2. Build:
   mvn clean install

3. Executar:
   mvn spring-boot:run
   ou (após build)
   java -jar target\WFgeren-1-0.0.1-SNAPSHOT.jar

4. Testes:
   mvn test

5. Acessos úteis (padrões)
   - API: http://localhost:8080/
   - Swagger UI (se dependência adicionada): http://localhost:8080/swagger-ui/index.html
   - H2 Console (se habilitado no application.yml): caminho configurado em `application.yml`

## Autenticação (JWT)
Fluxo básico:
1. POST /api/auth/login com JSON { "username": "...", "password": "..." }.
2. Receber token JWT no corpo/resposta.
3. Em chamadas protegidas, adicionar header:
   Authorization: Bearer <token>

Verifique `Config/SecurityConfig.java`, `Config/JwtService.java` e `Config/JwtUtils.java` para detalhes da implementação.

## Configuração do Swagger (se ainda não adicionado)
Adicionar ao `pom.xml`:
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
</dependency>
```
Após adicionar, reinicie a aplicação e acesse o Swagger UI em /swagger-ui/index.html.

## Observações importantes / Pendências
- Garantir que os métodos dos Controllers usem `@Valid` nos DTOs para ativar validação automática.
- Implementar/ajustar filtros de segurança para proteger endpoints sensíveis (inventário, criação/remoção de recursos).
- Adicionar exemplos de query params e endpoints de busca/filtragem.
- Confirmar e ajustar configurações em `application.yml` (datasource, porta, propriedades JWT).
- Caso queira testes de integração, adicionar configuração de banco em memória (H2) para os testes.

## Estrutura de diretórios (resumo)
- src/main/java/com/br/WFgeren/
  - Config/ (segurança, jwt, openapi)
  - controller/
  - DTO/
  - Exception/
  - model/
  - repository/
  - service/
- src/main/resources/
  - application.yml

## Contato / Próximos passos
- Para finalizar o projeto: adicionar dependência do springdoc, revisar controllers para `@Valid`, proteger endpoints, e adicionar endpoints de filtragem por query params.
- Posso gerar exemplos prontos (README, dependência pom.xml, exemplos de requests para login e uso do JWT, e exemplos de controllers com `@Valid`) — diga qual deseja primeiro.
