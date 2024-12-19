# Spring Boot Microservices Project

## Overview
This project demonstrates how to build, package, and deploy a microservices-based application using Spring Boot, Docker, and Kubernetes. It covers building REST APIs, integrating with PostgreSQL, using Spring Cloud for service discovery, and implementing fault tolerance with Resilience4J. The project includes the creation of multiple services, including Job, Company, and Review, along with inter-service communication via Eureka and OpenFeign.

## Project Description
This project is a Spring Boot-based microservices application focused on managing jobs, companies, and reviews. It includes multiple microservices that communicate with each other via REST APIs, with support for fault tolerance, service discovery, distributed tracing, and messaging through RabbitMQ. The application uses Docker for containerization and Kubernetes for orchestration.

## Tech Stack
- **Backend**: Java 17, Spring Boot 2.6.x
- **Database**: PostgreSQL, H2 (for testing)
- **Service Discovery**: Eureka
- **Inter-Service Communication**: RestTemplate, OpenFeign
- **Fault Tolerance**: Resilience4J (Circuit Breaker, Retry, Rate Limiting)
- **Messaging**: RabbitMQ
- **Distributed Tracing**: Zipkin
- **API Gateway**: Spring Cloud Gateway
- **Docker**: Docker Compose, Dockerfile for each service
- **Kubernetes**: Minikube, Kubernetes Dashboard

## Installation

### Prerequisites
- Java 17
- Docker (for containerization)
- Kubernetes (for orchestration)
- Maven or Gradle
- PostgreSQL (or Dockerized PostgreSQL)

### Steps to Set Up Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/balajiganesan1220/spring-boot-microservice.git
