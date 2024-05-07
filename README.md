# Spring MVC E-Notes

* This is a Spring Boot application using MVC model.
* The application has the below functionalities
    * View all the Notes.
    * Add new Note.
    * Update existing Note.
    * Delete a Note.

## Technologies and tools used

* Spring Boot
* MySQL

## Required dependencies

* Spring Data JPA
* Spring Web
* MySQL Driver
* Thymeleaf
* Bootstrap
* Spring Security


## Add the below code in application.properties

    spring.application.name=EnotesSpringBoot
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/enotes
    spring.datasource.username=root
    spring.datasource.password=password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect