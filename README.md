# Projeto Final Bloco 02 - API de Farmácia

<div align="center">
    <img src="https://i.imgur.com/w8tTOuT.png" alt="Logo Spring Boot" />
</div>

<div align="center">
  <img src="https://img.shields.io/github/languages/top/Brunogodoy2911/projeto_final_bloco_02?style=flat-square"/>
  <img src="https://img.shields.io/github/repo-size/Brunogodoy2911/projeto_final_bloco_02?style=flat-square"/>
  <img src="https://img.shields.io/github/languages/count/Brunogodoy2911/projeto_final_bloco_02?style=flat-square"/>
  <img src="https://img.shields.io/github/last-commit/Brunogodoy2911/projeto_final_bloco_02?style=flat-square"/>
  <img src="https://img.shields.io/github/issues/Brunogodoy2911/projeto_final_bloco_02?style=flat-square"/>
  <img src="https://img.shields.io/github/issues-pr/Brunogodoy2911/projeto_final_bloco_02?style=flat-square"/>
  <img src="https://img.shields.io/badge/status-concluído-green?style=flat-square"/>
</div>

---

## 1. Descrição

A **API de Farmácia** é uma aplicação backend desenvolvida como projeto final do Bloco 02 do Bootcamp da Generation Brasil. Ela permite o gerenciamento de informações de uma farmácia, como produtos e categorias, através de uma API RESTful, usando **Java** e **Spring Boot**.

### Funcionalidades principais:
- CRUD de produtos e categorias.
- Associação de produtos a categorias.
- Busca e filtro de produtos.
- Autenticação e autorização (opcional).

---

## 2. Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Security opcional)
- **JPA + Hibernate**
- **MySQL** ou **H2 Database**
- **Maven**
- **Docker** (opcional)
- **Swagger/OpenAPI**

---

## 3. Como Executar

### Pré-requisitos:
- Java JDK 17+
- Maven 3.6+
- MySQL (ou H2)
- Git
- (Opcional) Docker e Postman

### Passos:
```bash
git clone https://github.com/Brunogodoy2911/projeto_final_bloco_02.git
cd projeto_final_bloco_02
```

Configure o banco em `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_farmacia?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

Execute:
```bash
./mvnw spring-boot:run
```
Acesse: `http://localhost:8080`

---

## 4. Documentação
Acesse o Swagger UI em:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 5. Contato

**Bruno Godoy**  
[GitHub](https://github.com/Brunogodoy2911) | [LinkedIn](https://www.linkedin.com/in/brunogodoydev/)
