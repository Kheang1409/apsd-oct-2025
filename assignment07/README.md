# ADS Web API — Based on CityLibrary Example

This repository contains a City Library Web API example (from Lesson 7) that can be used as a starting point for implementing the ADS (Dental Surgeries Appointments) RESTful Web API required for Lab Assignment 7.

This README summarizes what is present, the files to inspect, how to run the application, and how to implement/test the required ADS endpoints.

## What I checked

- `Instruction.txt` — contains the Lab Assignment 7 requirements (list of REST endpoints to implement for Patients and Addresses).
- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/CitylibrarywebapiApplication.java` — the Spring Boot application entrypoint. It uses the standard Spring Boot `@SpringBootApplication` and prints an informational message on startup.
- Project structure includes the usual MVC layers under `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/`:
  - `controller/` — contains controllers (e.g., `AddressController.java`, `PatientController.java`, `PublisherController.java`).
  - `model/` — domain classes (e.g., `Patient.java`, `Address.java`, `Publisher.java`, `Book.java`, etc.).
  - `repository/` — Spring Data repositories (e.g., `PatientRepository.java`, `AddressRepository.java`).
  - `service/` and `service/impl/` — service layer implementations.
  - `exception/` and `advice/` — custom exceptions and centralized exception handlers.

These components make this project a good template for implementing the ADS endpoints required by the assignment.

## Required ADS endpoints (from `Instruction.txt`)

The assignment requires implementing the following REST endpoints (base path shown as `http://localhost:8080/adsweb/api/v1`):

1. GET /patients
   - Return all Patients including their primaryAddresses, sorted by lastName (ascending).
2. GET /patients/{id}
   - Return patient by id including primaryAddress. Must handle not-found with a proper exception and HTTP 404.
3. POST /patients
   - Create a new Patient. Body: JSON patient payload.
4. PUT /patient/{id}
   - Update an existing Patient by id. Must handle not-found with a proper exception and HTTP 404.
5. DELETE /patient/{id}
   - Delete a Patient by id.
6. GET /patient/search/{searchString}
   - Search patients by provided searchString (match on relevant fields).
7. GET /addresses
   - Return all Addresses including Patient data, sorted by city (ascending).

Note: The example project uses a different base path (`/citylibrary/api/v1/...`), so you will need to either:

- adapt controllers to use the `adsweb` base path, or
- add new ADS controllers that reuse the models/services/repositories.

## How to run the application (Windows / PowerShell)

1. From the project root (where `pom.xml` is located):

```powershell
# Run with the Maven wrapper (Windows cmd variant)
.\mvnw.cmd spring-boot:run
```

or build a jar and run it:

```powershell
.\mvnw.cmd clean package -DskipTests
java -jar target\citylibrarywebapi-0.0.1-SNAPSHOT.jar
```

The application runs by default on port 8080. The current example's startup message references a publisher list endpoint (`/citylibrary/api/v1/publisher/list`).

## How to test the ADS endpoints (examples)

Replace {id} and payloads as needed. Example base URL: `http://localhost:8080/adsweb/api/v1`.

- List patients
  GET http://localhost:8080/adsweb/api/v1/patients

- Get patient by id
  GET http://localhost:8080/adsweb/api/v1/patients/1

- Create patient (POST)
  POST http://localhost:8080/adsweb/api/v1/patients
  Body (application/json):
  {
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com",
  "primaryAddress": {
  "street": "123 Main St",
  "city": "Anytown",
  "state": "WA",
  "zipCode": "98000"
  }
  }

- Update patient (PUT)
  PUT http://localhost:8080/adsweb/api/v1/patient/1
  Body: full or partial patient JSON (as your controller expects).

- Delete patient
  DELETE http://localhost:8080/adsweb/api/v1/patient/1

- Search patients
  GET http://localhost:8080/adsweb/api/v1/patient/search/Smith

- List addresses
  GET http://localhost:8080/adsweb/api/v1/addresses

Use Postman or curl for testing. Example curl (PowerShell note: use single quotes carefully):

```powershell
curl -X GET "http://localhost:8080/adsweb/api/v1/patients" -H "Accept: application/json"
```

## Files to inspect / modify to implement ADS endpoints

- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/controller/PatientController.java`
  - This controller probably already contains patient-related endpoints you can adapt.
- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/model/Patient.java`
- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/model/Address.java`
- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/repository/PatientRepository.java`
- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/service/` and `service/impl/`
- `src/main/java/edu/miu/cs/cs489/lesson7/citylibraryapp/exception/` and `advice/` for consistent error handling.

## Implementation hints and acceptance criteria

- Ensure the GET /patients endpoint returns patients sorted by `lastName` ascending and contains the primary address (use eager fetch or DTO mapping).
- For GET by id, PUT, and DELETE, return 404 when `id` not found. Use a custom exception (e.g., `PatientNotFoundException`) and the existing `advice` handler to return proper HTTP statuses.
- POST should validate the payload and return 201 Created with Location header pointing to the new resource (optional but recommended).
- Implement search to perform case-insensitive matching on fields such as firstName, lastName, email, or address fields.
- For GET /addresses return list sorted by `city` ascending and include patient information.

## Screenshots

Create a `screenshots/` folder (already present in workspace). Save Postman response screenshots there, e.g., `screenshots/patients-list.png`, and include them in your submission.

## Next steps (recommended)

1. Implement or adapt `PatientController` endpoints to the `adsweb` base path and ensure services/repositories are wired.
2. Add/adjust DTOs if you prefer not to expose entities directly.
3. Add unit/integration tests for the controller/service layer (happy path + not-found cases).
4. Use Postman to capture screenshots and store them in `screenshots/`.

---

If you want, I can also:

- Map out exact controller method signatures for each ADS endpoint based on the existing `PatientController.java` in the project.
- Implement the ADS controllers and tests directly in this repository.
- Run the app to verify the endpoints and capture sample responses.

Tell me which of the above you'd like me to do next.
