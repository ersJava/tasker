# Tasker

## How It Works

A simple service that allows CRUD operations for a simple task-tracking web service and incorporates an ad server that serves up an advertisement with every task.

Please see YAML for API documentation.

## Project Details

Config server: https://github.com/ersJava/tasker-config-server

This application implements REST microservices and Feign web service clients. The Tasker Service is a typical Spring Boot REST web service with a controller, DAO (utilizing Jdbc template and prepared statements), and service layer components and acts as the edge service for the system with Eureka. Exceptions are handled through Controller Advice and return proper HTTP status codes and data when exception occur.

Every Task that is created will return an advertisement with that task. The advertisements are randomly generated through the Adserver Service.

### Technologies Used
* Java
* Spring Boot
* Feign
* Eureka Server
* MySQL
* Mockito
