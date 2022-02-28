# Payments-API

## Description

This is a Payments/Receipts API created as an exercise to apply what I learned from Java technology with Spring Boot Framework.
Basically with this api it is possible to manipulate user data, payments, receipts and items according to the CRUD methods of a Rest API.

the Crud methods

**NOTE**: This API is Maven and required Java SE JDK 11 (We use 11.0.9). Install the Maven dependencies and run.

## Tecnologias utilizadas:
* Spring Boot (last version),
* Maven (2.6.3),
* JPA (ORM),
* Postgres (DB),
* Lombok (1.18.22).

## How to run in Development Environment?

Create **Postgres Database** using docker command:

```bash
docker run -d -i -t --name crudjavapostgres -ePOSTGRES_PASSWORD=passw -ePOSTGRES_DATABASE=crudjavapostgres -ePOSTGRES_USER=username -p5432:5432 postgres
```
