# ADS Dental Surgeries Web API

Spring Boot Web API with JWT-based authentication and role-based authorization.

- Java 22 (toolchain includes JDK 22 and 25)
- Maven 3.9.1+
- Spring Boot 3.3.x
- Spring Security + JWT (jjwt)
- OpenAPI/Swagger UI (springdoc)
- H2 in-memory DB for dev

## Run

1. Ensure Maven 3.9.1+ and JDK 22 are installed. Optionally copy `maven-toolchains.sample.xml` to `%USERPROFILE%/.m2/toolchains.xml` and adjust JDK paths.
2. Build and run:

```
./mvnw spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui/index.html

Auth:

- POST /api/auth/register { username, password, role }
- POST /api/auth/login { username, password }

Use the returned JWT in Authorization: Bearer <token>

Seeded user: admin / admin123 (OFFICE_MANAGER)
