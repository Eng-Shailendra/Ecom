# E-Commerce Backend Service (`ecom`)

A scalable and robust e-commerce backend service built using **Java 24**, **Spring Boot 4.1.1**, and **MySQL**. This 
application handles core e-commerce modules, including user authentication with dynamic security parameters, registration validation rules, and transactional persistence layers using Spring Data JPA.

---

## 🚀 Key Features

* **Data Architecture:** Layered framework containing clear demarcations across Controllers, Service Implementations, and Entities (`User`, `ProductEntity`).
* **Fail-Fast Registration:** Input validation algorithms check data health (`@Valid`, `@NotBlank`) before initiating heavy relational database connections.
* **JPA Persistence:** Automatic relational schema management using Hibernate's `ddl-auto: update` strategy.
* **API Protection:** Integrated Spring Security configuration handling session context mapping and development filters.

---

## 🛠️ Tech Stack & Requirements

* **Language:** Java 24
* **Framework:** Spring Boot 4.1.1 (with Spring Data JPA & Spring Security)
* **Database:** MySQL 8.0.0
* **Connection Pooling:** HikariCP
* **API Testing:** Postman

---

## 📦 Getting Started & Installation

### 1. Database Setup
Log into your local MySQL server instance and create the target schema:
```sql
CREATE DATABASE ecom_content;
```

### 2. Application Configuration
Create an `application.properties` file inside `src/main/resources/`. Add your local environmental properties (ensure this file is blocked from your public Git tree using `.gitignore`):

```properties
spring.application.name=ecom

# Datasource Settings
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/ecom_content?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Options
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Security Settings for Local Development Testing
spring.security.user.name=user
spring.security.user.password={noop}YOUR_STATIC_PASSWORD
```

### 3. Running the Server
Execute the application from your IDE (IntelliJ IDEA) or via the terminal command line wrapper:
```bash
./mvnw spring-boot:run
```
The embedded Tomcat container will initialize on port **`8080`**.

---

## 🧪 API Endpoint Specifications (Postman Testing)

### **User Registration**
* **Endpoint:** `POST http://localhost:8080/register`
* **Headers:** `Content-Type: application/json`
* **Authorization Type:** Set to `No Auth` if security exceptions are bypassed, or use `Basic Auth` with dev properties.

**Sample Request Body (JSON Case Sensitive):**
```json
{
    "email": "suraj.test@example.com",
    "password": "mySecurePassword123",
    "username": "Suraj99",
    "fullname": "Suraj Kumar"
}
```

**Expected Response Code:** `201 Created`

---

## 🛡️ Security & Git Best Practices
To prevent database connection secrets from being exposed on remote branches, ensure that tracking rules ignore property trees:

Add the following to your root level `.gitignore`:
```text
### Spring Boot Configuration ###
**/src/main/resources/application.properties
**/src/main/resources/application.yml
```




