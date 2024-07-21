# Personal CRM Project: Coding Style and Architectural Choices

## 1. Project Structure
- Use-case based package structure
- Each major feature has its own package
- Use cases are separated into individual packages within feature packages

Example:
```
com.yourcompany.personalcrm/
├── config/
│   └── JdbiConfiguration.java
├── contact/
│   ├── addcontact/
│   │   ├── AddContactRequest.java
│   │   ├── AddContactResponse.java
│   │   ├── AddContactUseCase.java
│   │   ├── AddContactRepository.java
│   │   └── AddContactController.java
│   └── listcontactsummaries/
│   │   ├── ListContactSummariesRequest.java
│   │   ├── ListContactSummariesResponse.java
│   │   ├── ListContactSummariesUseCase.java
│   │   ├── ListContactSummariesRepository.java
│   │   └── ListContactSummariesController.java
├── interaction/
├── relationship/
└── ...
```

## 2. Coding Style

### 2.1 Braces
- Use Allman style braces
  - Opening brace on a new line
  - Closing brace aligned with the opening statement

Example:
```java
public class Example
{
    public void method()
    {
        if (condition)
        {
            // code
        }
    }
}
```

### 2.2 Naming Conventions

- **Classes:** PascalCase
- **Methods and variables:** camelCase
- **Constants:** UPPER_SNAKE_CASE

### 2.3 Field Declarations

All fields in model objects are public and final
Use Lombok's `@AllArgsConstructor` for constructors

Example:
```java
@AllArgsConstructor
public class AddContactRequest
{
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;
    public final String company;
}
```

### 2.4 Getters and Setters

Do not use getters and setters for model objects
Access fields directly

## 3. Architectural Choices

### 3.1 Clean Architecture

Separation of concerns
Independence of frameworks
Testability

### 3.2 Use Case Driven Design

Each use case is encapsulated in its own package.
Use case classes handle business logic.
For simple operations with a single parameter (e.g., delete by ID), pass the parameter directly to the use case and controller methods.
For operations with multiple or complex parameters, use request objects.

### 3.3 Dependency Injection

Use constructor injection for dependencies

### 3.4 Database

Use PostgreSQL for production.
Use H2 with PostgreSQL compatibility mode for development and testing.
Use Liquibase for database migrations.

### 3.4.1 Database Access

- Use JDBI SQL Objects for database operations
- Repository interfaces should use JDBI annotations (@SqlUpdate, @SqlQuery, etc.)
- Use cases should depend on Jdbi to create repository instances

### 3.5 API

RESTful API design
Use Spring Boot for API implementation

### 3.6 Testing

JUnit for unit tests
Cucumber and Selenium with Serenity for integration tests

### 3.7 ID Generation

Use database-generated primary keys
Store IDs as Strings in the domain model for future flexibility

### 3.8 Frontend

Use simple HTML with Mustache templates

### 3.9 API Documentation

Use Swagger UI for API documentation and testing

## 4. Technologies and Frameworks

Java 21
Spring Boot (latest version)
Lombok
JDBI (for SQL Object interfaces)
H2 Database (for development and testing)
PostgreSQL (for production)
Liquibase
JUnit
Cucumber
Selenium
Serenity
Swagger UI (SpringDoc OpenAPI)

## 5. Build and Deployment

Use Maven for build management
Containerize application using Docker
Deploy on Kubernetes

## 6. Maven Configuration

Use maven.compiler.release to set Java version
Enable parameter name retention with maven.compiler.parameters

## 7. Jackson Configuration

Use property-based creators by default
Configure Jackson to work with Lombok-generated constructors

## 8. Security

Currently configured to permit all requests for development
Proper security measures to be implemented for production

## 9. API Documentation

### 9.1 Swagger/OpenAPI Annotations

We use Swagger (OpenAPI) annotations to provide clear and interactive API documentation.

- Use `@Tag` at the controller level to group related operations by their core entity name.
- Use `@Operation` for each API endpoint to provide a summary and description.
- Use `@Parameter` to describe path, query, and header parameters.
- Use `@RequestBody` to describe the request body structure.
- Use `@ApiResponse` to document possible response statuses and their meanings.

Example:

```java
@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contact", description = "Contact management APIs")
public class UpdateContactController
{
    @PutMapping
    @Operation(summary = "Update contact", 
               description = "Updates an existing contact's information")
    @ApiResponse(responseCode = "200", description = "Contact updated successfully")
    @ApiResponse(responseCode = "404", description = "Contact not found")
    public ResponseEntity<Void> updateContact(
        @RequestBody @Valid UpdateContactRequest request)
    {
        // Method implementation
    }
}
```

### 9.2 Swagger UI

Swagger UI is automatically available at `/swagger-ui.html` when the application is running.
Use Swagger UI for testing and exploring the API during development.

## 10. Errata

This document is subject to updates and modifications as the project evolves.