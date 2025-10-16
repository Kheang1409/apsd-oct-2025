# ADS Dental Surgeries Web API - Deployment Guide

## Project Information

- **Project Name**: ADS Dental Surgeries Web API
- **Version**: 0.0.1-SNAPSHOT
- **Java Version**: 21
- **Spring Boot Version**: 3.3.4
- **Maven Version**: 3.9.11+ (required 3.9.1+)

## Build & Run

### Prerequisites

- JDK 21 or higher installed
- Maven 3.9.1 or higher

### Build the Application

```bash
cd d:\MIU\Courses\ApSD\apsd-oct-2025\assignment09\ads-dentail-surgeries-web-api
mvn clean package -DskipTests
```

### Run the Application

```bash
java -jar target\ads-dentail-surgeries-web-api-0.0.1-SNAPSHOT.jar
```

Or using Maven:

```bash
mvn clean compile exec:java -Dexec.mainClass="asd.lab9.ads_dentail_surgeries_web_api.AdsDentalSurgeriesWebApiApplication"
```

The application will start on **http://localhost:8080**

## Application Endpoints

### Swagger/OpenAPI Documentation

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **API Docs (JSON)**: http://localhost:8080/v3/api-docs
- **API Docs (YAML)**: http://localhost:8080/v3/api-docs.yaml

### H2 Database Console

- **H2 Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:adsdb`
- **Username**: `sa`
- **Password**: _(empty)_

### Authentication Endpoints

- **POST** `/api/auth/register` - Register a new user
- **POST** `/api/auth/login` - Login and get JWT token

### Appointment Endpoints (Requires Authentication)

- **GET** `/api/appointments` - Get all appointments (PATIENT, DENTIST, OFFICE_MANAGER)
- **POST** `/api/appointments` - Create a new appointment (OFFICE_MANAGER)

## Testing the Application

### 1. Register a New User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"testuser\",\"password\":\"password123\"}"
```

### 2. Login and Get JWT Token

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
```

Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin",
  "roles": ["OFFICE_MANAGER"]
}
```

### 3. Access Protected Endpoints

Use the JWT token from login response:

```bash
curl -X GET http://localhost:8080/api/appointments \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

## Default Users

The application is seeded with default roles and an admin user:

### Roles

- **PATIENT**
- **DENTIST**
- **OFFICE_MANAGER**

### Admin User

- **Username**: `admin`
- **Password**: `admin123`
- **Roles**: OFFICE_MANAGER

## Features Implemented

### ✅ Domain Model

- User (base class) with Patient and Dentist as subclasses
- JOINED inheritance strategy
- Many-to-Many relationship between Users and Roles
- Address, Service, Surgery, Bill, Appointment entities

### ✅ Security

- JWT-based authentication
- Role-based authorization
- BCrypt password encoding
- Custom UserDetailsService
- JWT filter for token validation

### ✅ Repository Layer

- Spring Data JPA repositories
- UserRepository, RoleRepository, AppointmentRepository

### ✅ Service Layer

- Authentication service (via AuthController)
- Custom user details service

### ✅ REST API

- AuthController for registration and login
- AppointmentController with role-based access
- DTOs for requests and responses

### ✅ Documentation

- Swagger/OpenAPI integration
- API documentation with JWT bearer authentication

### ✅ Database

- H2 in-memory database for development
- Automatic schema generation
- Data seeding on startup

## Configuration

Key application properties (`src/main/resources/application.properties`):

```properties
# Server Configuration
server.port=8080

# H2 Database
spring.datasource.url=jdbc:h2:mem:adsdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=yourSecretKeyForJWTTokenGenerationMustBeAtLeast256BitsLongForHS256Algorithm
jwt.expiration=86400000
```

## Notes

### Lombok Issue Resolution

- Initially configured with Lombok for Java 22, encountered compatibility issues
- Lombok 1.18.34 has TypeTag errors with Java 21/22
- **Solution**: Removed Lombok annotations and manually implemented getters/setters/builder pattern
- Changed from Java 22 to Java 21 (LTS) for better stability

### Architecture Decisions

- **Inheritance Strategy**: JOINED table per class hierarchy for User → Patient/Dentist
- **Security**: JWT stateless authentication with role-based authorization
- **Database**: H2 for development, easily switchable to MySQL/PostgreSQL for production
- **API Documentation**: Swagger/OpenAPI with JWT bearer token support

## Future Enhancements

- Add unit and integration tests
- Implement CRUD operations for all entities
- Add pagination for list endpoints
- Implement refresh token mechanism
- Add logging and monitoring
- Configure profiles for dev/test/prod environments
- Switch to production database (MySQL/PostgreSQL)

## Troubleshooting

### Application Won't Start

- Ensure JDK 21+ is installed and in PATH
- Check if port 8080 is available
- Run `mvn clean package` to rebuild

### JWT Token Invalid

- Check token expiration (24 hours by default)
- Ensure token is sent in Authorization header: `Bearer <token>`
- Verify jwt.secret matches between token generation and validation

### Database Connection Issues

- H2 console might not show data if not accessed immediately after startup
- Use JDBC URL: `jdbc:h2:mem:adsdb`

## Contact

For questions or issues, please refer to the project README.md
