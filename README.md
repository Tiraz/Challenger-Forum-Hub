<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>FórumHub</title>
</head>
<body>

<h1>FórumHub
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
</h1>

<p>Projeto desenvolvido como <strong>challenge da Formação One</strong> da <strong>Alura + Oracle</strong>.</p>

<p>O <strong>FórumHub</strong> é uma API REST em <strong>Java</strong> com <strong>Spring Boot</strong> para gerenciamento de tópicos de fórum, permitindo criar, listar, atualizar e excluir tópicos de forma segura e organizada.</p>

<hr>

<h2>Funcionalidades</h2>
<ul>
  <li><strong>Criar tópico:</strong> Usuários podem abrir novos tópicos com título, mensagem e curso.</li>
  <li><strong>Listar tópicos:</strong> Retorna todos os tópicos cadastrados (com paginação).</li>
  <li><strong>Consultar tópico específico:</strong> Busca um tópico pelo <code>id</code>.</li>
  <li><strong>Atualizar tópico:</strong> Editar título, mensagem, curso ou status.</li>
  <li><strong>Deletar tópico:</strong> Exclui um tópico do fórum.</li>
  <li><strong>Autenticação JWT:</strong> Apenas usuários autenticados podem acessar as rotas protegidas.</li>
</ul>

<hr>

<h2>Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17 - Linguagem principal</li>
  <li>Spring Boot - Framework principal</li>
  <li>Spring Data JPA - Persistência com ORM</li>
  <li>Spring Security + JWT - Autenticação e autorização</li>
  <li>PostgreSQL - Banco de dados relacional</li>
  <li>Lombok - Redução de boilerplate code</li>
</ul>

<hr>

<h2>Configuração do Banco de Dados (Spring Boot)</h2>

<p>Exemplo de <code>application.properties</code> usando variáveis de ambiente:</p>

<pre>
spring.datasource.url=jdbc:postgresql://${DB_HOST}/forumhub_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true

api.security.token.secret=${JWT_SECRET:12345678}
</pre>

<h3>Explicação dos parâmetros</h3>
<ul>
  <li><code>${DB_HOST}</code> → Host e porta do PostgreSQL (ex.: localhost:5432)</li>
  <li><code>${DB_USER}</code> → Usuário do banco</li>
  <li><code>${DB_PASSWORD}</code> → Senha do banco</li>
  <li><code>hibernate.dialect</code> → Dialeto do Hibernate para PostgreSQL</li>
  <li><code>spring.jpa.hibernate.ddl-auto=update</code> → Atualiza automaticamente o esquema do banco</li>
  <li><code>spring.jpa.show-sql=true</code> → Exibe os comandos SQL no console</li>
  <li><code>spring.jpa.format-sql=true</code> → Formata os comandos SQL para melhor leitura</li>
  <li><code>api.security.token.secret</code> → Chave secreta usada no JWT</li>
</ul>

<p><strong>💡 Dica:</strong> Crie o banco manualmente antes de rodar o projeto:</p>
<pre>
CREATE DATABASE forumhub_db;
</pre>

<hr>

<h2>Estrutura do Projeto</h2>
<ul>
  <li><code>topico/modelo</code> → Entidades JPA (<code>Topico</code>, <code>Usuario</code>)</li>
  <li><code>topico/dto</code> → DTOs para entrada e saída de dados</li>
  <li><code>repositorio</code> → Repositórios JPA</li>
  <li><code>controller</code> → Endpoints REST</li>
  <li><code>security</code> → Configuração de autenticação JWT</li>
</ul>

<hr>

<h2>Exemplos de Requisição</h2>

<h3>🔹 Criar tópico (POST)</h3>
<pre>
POST /topicos
{
  "titulo": "Duvida sobre Spring Security",
  "mensagem": "Como configurar autenticação JWT?",
  "curso": "BACKEND",
  "autor": {
    "nome": "João",
    "senha": "123456"
  }
}
</pre>

<h3>🔹 Listar tópicos (GET)</h3>
<pre>
GET /topicos

[
  {
    "id": 1,
    "titulo": "Duvida 1",
    "mensagem": "Erro ao configurar JPA",
    "curso": "BACKEND",
    "status": "PENDENTE",
    "nome": "Maria"
  }
]
</pre>

<hr>

<h2>Como Rodar</h2>
<ol>
  <li>Clone o repositório:
    <pre>git clone &lt;URL_DO_REPOSITORIO&gt;</pre>
  </li>
  <li>Configure o PostgreSQL e crie o banco:
    <pre>CREATE DATABASE forumhub_db;</pre>
  </li>
  <li>Configure as variáveis de ambiente:
    <pre>
export DB_HOST=localhost:5432
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
export JWT_SECRET=12345678
    </pre>
  </li>
  <li>Abra o projeto em sua IDE favorita (IntelliJ, Eclipse, VS Code).</li>
  <li>Execute a aplicação com:
    <pre>mvn spring-boot:run</pre>
  </li>
  <li>Acesse a API em:
    <pre>http://localhost:8080</pre>
  </li>
</ol>

<hr>

<h2>Observações</h2>
<ul>
  <li>O projeto implementa autenticação JWT para segurança.</li>
  <li>Os enums <code>Cursos</code> e <code>StatusTopico</code> foram criados para padronizar os valores permitidos.</li>
  <li>Este projeto foi construído como parte do desafio <strong>Challenge Back End - FórumHub</strong> da Alura + Oracle.</li>
</ul>

</body>
</html>
