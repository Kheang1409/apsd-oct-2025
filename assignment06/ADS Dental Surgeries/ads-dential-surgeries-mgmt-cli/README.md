# ADS Dental Surgeries — Management CLI (Spring Boot)

This is a small Spring Boot demo application (command-line / self-seeding application) for managing dental surgeries, dentists, patients, appointments and bills.

The project was created for the ADS practical assignment and includes both JPA-backed repositories (for production) and in-memory repository implementations (for testing/quick runs).

## Quick facts

- Artifact: `ads-dential-surgeries-mgmt-cli`
- GroupId: `asd.lab6`
- Java: 21
- Spring Boot parent: 3.5.6

## Prerequisites

- Java 21 (or a compatible JDK)
- Maven (wrapper included, you'll usually use the project's `mvnw` / `mvnw.cmd`)
- PostgreSQL if you want to run with the `prod` profile (default configuration uses PostgreSQL)

Note: The repository also includes an in-memory implementation of repositories that can be enabled using the `test` profile — no database required for that profile.

## Build

From the project root (Windows PowerShell):

```powershell
.\mvnw.cmd clean package -DskipTests
```

Or run with the wrapper without packaging:

```powershell
.\mvnw.cmd spring-boot:run
```

## Run (profiles)

The application selects repository beans by Spring profile:

- `prod` — JPA-backed repositories (uses PostgreSQL). This is the default profile in `src/main/resources/application.properties`.
- `test` — in-memory repository implementations (no database required). Useful for development or CI where you don't want to run Postgres.

To run using the `test` profile (in-memory):

```powershell
.\mvnw.cmd -Dspring-boot.run.profiles=test spring-boot:run
```

To run using the `prod` profile (PostgreSQL):

```powershell
.\mvnw.cmd -Dspring-boot.run.profiles=prod spring-boot:run
```

Or after packaging:

```powershell
java -jar target\ads-dential-surgeries-mgmt-cli-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

## Default configuration

The shipped `application.properties` (in `src/main/resources`) sets:

- `spring.profiles.active=prod`
- A local PostgreSQL connection: `jdbc:postgresql://localhost:5432/postgresTest`
- username=`postgres`, password=`Password_123#` (change these for your environment)

If you don't have PostgreSQL running locally, either start it and adjust the connection settings, or run with `-Dspring.profiles.active=test` to use in-memory repositories.

## Seeded sample data

On application startup the main `CommandLineRunner` (`AdsDentialSurgeriesMgmtCliApplication`) attempts to create or find sample data:

- Services: `Teeth Cleaning`, `Cavity Filling`
- Surgery: `Downtown Dental` (address `123 Main St, Springfield, IL 62701`)
- Dentist: user `alice` (role `DENTIST`)
- Patient: user `bob` (role `PATIENT`)
- A sample appointment and an associated bill (if not already present)

This behavior is intended to make the demo runnable immediately after startup.

## Running tests

Run unit/integration tests with Maven:

```powershell
.\mvnw.cmd test
```

## Project structure (high level)

- `src/main/java/asd/lab6/ads_dential_surgeries_mgmt_cli/domain` — domain entities (Patient, Dentist, Surgery, Appointment, Bill, Service, Address, User)
- `src/main/java/asd/lab6/ads_dential_surgeries_mgmt_cli/repository` — repository interfaces and two implementations:
  - `prod` — `Jpa*Repository` classes (annotated with `@Profile("prod")`)
  - `test` — `InMemory*Repository` classes (annotated with `@Profile("test")`)
- `src/main/java/.../service` — service layer (DentistService, PatientService, AppointmentService)
- `src/main/java/.../AdsDentialSurgeriesMgmtCliApplication.java` — application entry point and seed logic

## Helpful files

- `HELP.md` — additional notes generated when the project was created (contains a note about package naming)

## Security & credentials

The repository contains a default username/password used for seeding (`alice` / `password`, `bob` / `password`). These are for demo purposes only. Do not use these credentials in production.

## Notes & next steps

- If you intend to run against Postgres, create the database configured in `application.properties` or update the URL to point to an existing database.
- Consider externalizing DB credentials (environment variables or a secure vault) before deploying this application beyond local development.

---

If you'd like, I can also:

- add a `README` badge with build status,
- provide a small PowerShell script to run the app with the `test` profile, or
- update `application.properties` with environment-variable placeholders for credentials.
