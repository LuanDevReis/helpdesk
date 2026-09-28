# Helpdesk

Helpdesk is a Java/Spring Boot application for managing IT support tickets. It allows technicians and clients to create, track, and update service requests, with authentication, authorization, and structured API endpoints.

## Overview

This project is designed for internal IT service management, where:

- Clients can open support tickets
- Technicians can manage and update those tickets
- Ticket status, priority, and history are tracked
- A REST API exposes the system to frontend apps or internal tools
- Security is handled with JWT-based authentication

## Features

- User authentication and authorization
- Role-based access for clients and technicians
- Ticket management with priority and status tracking
- Support for storing customer and technician data
- RESTful API endpoints
- OpenAPI / Swagger documentation
- H2 database for local testing and MySQL for development
- Docker support for containerized deployment

## Tech Stack

- Java 25
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT (JJWT)
- H2 Database
- MySQL Connector
- SpringDoc OpenAPI
- Maven
- Docker

## Project Structure

```text
helpdesk/
├── .github/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/corecode/helpdesk/
│   │   │       ├── config/
│   │   │       ├── domain/
│   │   │       ├── repositories/
│   │   │       ├── resources/
│   │   │       ├── security/
│   │   │       └── services/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-docker.properties
│   │       └── application-test.properties
│   └── test/
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── system.properties
└── README.md
```

## Requirements

Before running the project, make sure you have:

- Java 25 or compatible version
- Maven
- Docker (optional, for containerized deployment)
- MySQL (optional, for development profile)

## Configuration

The application uses Spring profiles:

- `test`: uses H2 in-memory database
- `dev`: uses MySQL on localhost
- `docker`: intended for containerized environments

Main configuration is defined in `src/main/resources/application.properties`.

Example:

```properties
spring.profiles.active=test
server.port=${PORT:8080}

jwt.secret=${JWT_SECRET}
jwt.expiration=604800000
```

## Running the Application

### Using Maven

```bash
./mvnw clean install
./mvnw spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

### Using Docker

```bash
docker build -t helpdesk .
docker run -p 8080:8080 helpdesk
```

## API Documentation

The project includes Swagger/OpenAPI documentation.

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

## Authentication

The application uses JWT-based authentication for protected routes.

Environment variable used by the app:

```bash
export JWT_SECRET=your-secret-key
```

## Database

### Test profile

- H2 database in memory
- H2 console available at:

```text
http://localhost:8080/h2-console
```

### Development profile

The `dev` profile connects to MySQL using the following default configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/helpdesk
spring.datasource.username=root
spring.datasource.password=root
```

## Main Entities

The application includes these core domain models:

- `Cliente` - client users
- `Tecnico` - technicians
- `Chamado` - service tickets
- `Pessoa` - base entity for people in the system

Ticket data includes:

- opening date
- closing date
- priority
- status
- title
- observations
- linked client and technician

## License

This project does not currently include a license file. If you want, you can add one such as MIT or Apache 2.0.

## Contributing

Contributions are welcome. If you want to improve the project:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Submit a pull request

## Contact

For questions or suggestions, contact the project maintainer through the repository's GitHub profile.

---

This README was created to provide a clear English overview of the project and its setup.
