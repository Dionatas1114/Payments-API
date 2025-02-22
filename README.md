# 🏛 FINANCIAL API

## Description
This API is designed to address three financial needs:
- **User registration** 🙍‍♂️
- **A report on payments, receipts, and products** 🧾
- **A stock and crypto market monitor** 💹

### Prerequisites
- [Java 11 LTS](https://adoptium.net/temurin/releases/?version=11)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) (optional for database)
- [Git](https://git-scm.com/)

### Dependencies
* Spring Boot (2.7.10);
* JPA (ORM);
* Postgres (DB);
* Lombok (1.18.22);
* JUnit (5.8.2);
* Git (2.33.0);
* Docker (18.09.9);
* Swagger (2.9.2);

### Steps
1. Clone this repository:
   ```sh
   git clone https://github.com/Dionatas1114/Payments-API.git
   cd financial-api
   ```
   
2. Build Docker image using Dockerfile:
   - see `README.Docker.md`

3. Initialize the database:
   ```sh
   mvn flyway:migrate
   ```
   
4. Open in SQL Client (e.g. Dbeaver):
   * Connection URL: jdbc:postgresql://localhost:5432/payments
   * Username: username
   * Password: passw

5. Build the project:
   ```sh
   mvn clean install
   ```

6. Run the application:
   ```sh
   mvn spring-boot:run
   ```

7. Open Swagger UI:
   ```sh
   http://localhost:8080/swagger-ui/#/
   ```

8. Run the tests:
   ```sh
   mvn test
   ```

## How to remove local branches that do not exist on remote anymore?
using this command:

```bash
git branch -D (git branch --merged |% { $_.trim() } )
```

___
### How to run in Development Environment?
Create **Postgres Database** using docker command:

```bash
docker run -d -i -t --name payments -ePOSTGRES_PASSWORD=passw -ePOSTGRES_DATABASE=payments -ePOSTGRES_USER=username -p5432:5432 postgres
```

## Tables Structure

| Table Name           | Description                    |
|----------------------|--------------------------------|
| `users`              | Stores user information.       |
| `userConfigurations` | Stores userConfig information. |
| `products`           | Stores product information.    |
| `services`           | Stores service information.    |
| `payments`           | Stores payment information.    |
| `receipts`           | Stores receipt information.    |

## Project Structure

### Code files are organized as follows:
src/main/java/com/api/payments/**
- **Config**: Configuration classes.
- **Controller**: Handles HTTP requests and responses.
- **DTO**: Data Transfer Objects.
- **Entity**: Database entities.
- **Enum**: Enumerations.
- **Exception**: Custom exceptions.
- **Interceptor**: Interceptors.
- **Messages**: Success and Error messages.
- **Repository**: Data access and persistence.
- **Service**: Business logic and data access.
- **Utils**: Utility classes.
- **Validations**: Validation classes.

### Test files are organized as follows:
src/main/test/java/com/api/payments/**
- **Controller**: Unit tests.
- **Repository**: Unit tests.
- **Service**: Unit tests.
- **Validations**: Unit tests.