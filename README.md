# REST API for Smitch website 

This project is about Building REST APIs for Smitch website which can be used by user for  different services.

The company provides different services such as:
* Setting up user
* Adding power usage of the users

<br />

### This is a individual project, completed with 48 hours as part of placement assignment.

- **Services Offered**
1. User can Login and Signup.
2. User can add Power Usage
3. Get details of power usage Day wise

<br />

- **Backend**
1. Built authentication and authorization using Spring Security and UUID.
2. Stored the data on MySQL database

<br />

- **TechStack**
1. Java 8
2. Spring Boot
3. Spring Data JPA
4. MySql
5. Swagger

<br />

# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/smitch;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=98Bliss@48
```
<br />
🚀 The Swagger link to check rest Api-> http://localhost:8080/swagger-ui/
  
    
