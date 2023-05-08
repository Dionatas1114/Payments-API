# 🏛 FINANCIAL SOLUTIONS API

### Description

This is an API created to solve 3 financial problems:
 * I want a user record; 🙍‍♂️
 * I want a report of payments, receipts and products; 🧾
 * I want a stock exchange monitor; 💹

To develop these solutions, I used Java with Spring Boot.
___
### Technologies
* Java SE JDK 11 (11.0.9);
* Spring Boot (last version);
* Maven (2.6.3);
* JPA (ORM);
* Postgres (DB);
* Lombok (1.18.22);
___
### How to run in Development Environment?
Create **Postgres Database** using docker command:

```bash
docker run -d -i -t --name crudjavapostgres -ePOSTGRES_PASSWORD=passw -ePOSTGRES_DATABASE=crudjavapostgres -ePOSTGRES_USER=username -p5432:5432 postgres
```

___
### How to remove local branches that do not exist on remote anymore?
using this command:

```bash
git branch -D (git branch --merged |% { $_.trim() } )
```
