# **External API Integration Service**

## Spring Boot microservice for integration with external REST API (Wiremock), OpenAPI, Feign, MapStruct, Resilience4j, centralized error handling, integration tests.

### The project implements:

* Automatic client generation according to the specification (OpenAPI)
* Integration via Feign + Resilience4j RateLimiter
* Centralized error handler (GlobalExceptionHandler)
* DTO and mapping via MapStruct
* Integration and contract tests (WireMock, Testcontainers)
* Production-ready infrastructure (Docker, Docker Compose, healthchecks)
* Unified error format (ErrorDto)

### OpenAPI specification

(api/fake-external-api.yaml file in the project)

### Launch instructions

`docker compose up --build`

### Two containers will start:

integration-app (Spring Boot, port 8088)
fake-external-api (Wiremock, port 8081)

### Request examples

#### Get a list of cats:

`curl -H "X-API-Key: demo-secret-key" http://localhost:8088/api/v1/cats`

#### Get cat by id:

`curl -H "X-API-Key: demo-secret-key" http://localhost:8088/api/v1/cats/1`

#### Error (no API key):

`curl http://localhost:8088/api/v1/cats`