# WFgeren - Catálogo de Itens de Jogo com Inventário de Usuário

Visão geral
------------
WFgeren é uma API REST construída com Spring Boot para cadastrar, catalogar e gerenciar itens de um jogo.  
Principais entidades: Usuario, Inventario, Conjunto, Itens, Categoria.  
Relacionamentos principais:
- Usuario 1:1 Inventario
- Inventario N:N Conjunto
- Conjunto 1:N Itens
- Conjunto N:1 Categoria

O projeto já contém camadas Controllers, Services, Models, Repositories (Spring Data JPA), DTOs, validações via Bean Validation e um tratamento global de exceções. Implementação JWT já existe na pasta `Config/` (JwtService, JwtUtils, JwtAuthenticationFilter, SecurityConfig).

Pré-requisitos (qualquer máquina)
---------------------------------
- Java 17+ (JDK instalado e variável JAVA_HOME configurada)
- Maven 3.8+
- Git (opcional)
- Para execução simples sem DB externo: H2 (in-memory) — já suportado pela configuração sugerida abaixo
- Para produção/local com DB real: PostgreSQL / MySQL (ajustar variáveis de ambiente)

Variáveis de ambiente recomendadas
----------------------------------
- JWT_SECRET — segredo para assinatura dos tokens JWT (obrigatório em produção)
- SPRING_DATASOURCE_URL — JDBC URL do banco (opcional; se ausente, H2 em memória será usado)
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD
- SERVER_PORT (opcional; padrão 8080)
- SPRING_PROFILES_ACTIVE (opcional: dev/prod)

Exemplos (Windows PowerShell):
$env:JWT_SECRET="troque_por_uma_chave_segura"
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://dbhost:5432/wfgeren"
$env:SPRING_DATASOURCE_USERNAME="usuario"
$env:SPRING_DATASOURCE_PASSWORD="senha"

Exemplos (Linux/macOS):
export JWT_SECRET="troque_por_uma_chave_segura"
export SPRING_DATASOURCE_URL="jdbc:postgresql://dbhost:5432/wfgeren"

Configuração mínima sugerida (rodar em qualquer máquina)
--------------------------------------------------------
Para facilitar execução sem configurar banco externo, o projeto consegue rodar com H2 em memória. Adicione (ou verifique) no `src/main/resources/application.yml`:

spring:
  datasource:
    url: jdbc:h2:mem:wfgeren;DB_CLOSE_DELAY=-1
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
server:
  port: 8080
jwt:
  secret: ${JWT_SECRET:change_me}

Construir e executar
--------------------
1. Clonar repositório
- git clone <URL_DO_REPOSITORIO>
- cd WFgeren-1

2. Build
- mvn clean package

3. Executar (opções)
- Desenvolvimento (Maven):
  mvn spring-boot:run

- Executando JAR:
  java -jar target\<artifactId>-<version>.jar
  (ajuste o nome do JAR conforme o `target/` gerado)

- Executando com variáveis temporárias (PowerShell exemplo):
  $env:JWT_SECRET="minha_chave"; mvn spring-boot:run

- Passando propriedades ao iniciar o JAR:
  java -jar target\WFgeren-1-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:h2:mem:wfgeren --jwt.secret=minhaChave

Executar em Docker (opcional)
-----------------------------
Criar Dockerfile (simples) e usar imagem do JDK para rodar o JAR. Exemplo rápido:
- Build jar: mvn clean package
- Dockerfile mínimo:
  FROM eclipse-temurin:17-jdk
  COPY target/*.jar app.jar
  ENTRYPOINT ["java","-jar","/app.jar"]
- Build e run:
  docker build -t wfgeren .
  docker run -e JWT_SECRET=minhaChave -p 8080:8080 wfgeren

Swagger / OpenAPI (documentação)
-------------------------------
O projeto inclui `Config/OpenAPIConfig.java`. Para habilitar a UI do Swagger certifique-se de:
1. Adicionar dependência no `pom.xml` (se não existir):
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
</dependency>
```
2. Se Spring Security estiver ativo, liberar os endpoints do Swagger em `SecurityConfig`:
- /v3/api-docs/**, /swagger-ui/**, /swagger-ui.html

Após subir a aplicação, acessar:
- http://localhost:8080/swagger-ui/index.html
ou
- http://localhost:8080/v3/api-docs

Autenticação (JWT) — uso rápido
-------------------------------
1. Obter token:
- POST /api/auth/login
  Payload (exemplo):
  {
    "username": "usuario",
    "password": "senha"
  }

2. Usar token nas requisições protegidas:
- Header: Authorization: Bearer <token>

Observações:
- Verificar `Config/SecurityConfig.java` para rotas protegidas / liberadas.
- Se Swagger não estiver acessível por causa da segurança, ajustar SecurityConfig conforme acima.

Endpoints principais (resumo)
-----------------------------
- /api/auth/** — autenticação/registro
- /api/users/** — CRUD usuários
- /api/categorias/** — CRUD categorias
- /api/conjuntos/** — CRUD conjuntos
- /api/itens/** — CRUD itens
- /api/inventarios/** — operações de inventário

Validações e DTOs
-----------------
- DTOs com Bean Validation já existem em `DTO/`.  
- Para ativar validação nos controllers verifique se os métodos usam `@RequestBody @Valid` nos parâmetros de entrada.

Tratamento de erros
-------------------
- Handler global presente em `Exception/GlobalExceptionHandler.java`.

Testes
------
- Executar: mvn test
- Para testes que exigem DB use profile com H2 em memória (configurar `application-test.yml` se necessário).

Boas práticas para rodar em qualquer máquina
--------------------------------------------
- Usar variáveis de ambiente para segredos e credenciais (JWT_SECRET, SPRING_DATASOURCE_*).
- Preferir H2 em memória para ambiente de desenvolvimento/CI.
- Em produção, usar banco externo e configurar `hibernate.ddl-auto=validate`.
- Liberar endpoints do Swagger ao usar segurança para permitir inspeção da API.

Solução de problemas comuns
---------------------------
- Porta em uso: definir `SERVER_PORT` ou `server.port` em application.yml.
- Falta JWT_SECRET: definir variável de ambiente ou usar fallback temporário (não recomendado em produção).
- Swagger não aparece: verificar dependência `springdoc` e permissões no `SecurityConfig`.
- Erros de conexão DB: checar URL/credenciais e driver no classpath.

Licença
-------
Uso acadêmico / demonstrativo.