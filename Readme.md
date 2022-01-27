Api Gateway Management

Build Restful CRUD API for a simple Note-Taking application using Spring Boot, Mysql, JPA and Hibernate.

Requirements
Java -  17.0.1

Maven - 3.8.3

Mysql - 8.0.28

Spring Boot - 2.6.3

Steps to Setup
1. Clone the application

git clone https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial.git
2. Create Mysql database

create database gateways_app
3. Change mysql username and password as per your installation

open src/main/resources/application.properties

server.port= 8080

spring.datasource.url=jdbc:mysql://localhost:3306/gateways_app?createDatabaseIfNotExist=true&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=chile2022*
spring.datasource.initialization-mode=always

spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL55Dialect

change spring.datasource.username and spring.datasource.password as per your mysql installation

4. Build and run the app using maven

mvn package
java -jar target/gateway-app-1.0.0.jar
Alternatively, you can run the app without packaging it using -

mvn spring-boot:run
The app will start running at http://localhost:8080.

Explore Rest APIs
The app defines following CRUD APIs.

GET /api/v1/gateways

GET /api/v1/gateways/{gatewayId}

POST api/v1/gateways

POST /api/v1/gateways/{gatewayId}/device

DELETE /api/v1/gateways/{gatewayId}/device/{deviceId}

You can test them using postman or any other rest client.