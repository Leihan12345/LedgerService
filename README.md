# LedgerService

A double-entry ledger REST API in Java 21 / Spring Boot 3, built as a portfolio piece to demonstrate fundamentals: hexagonal architecture, transactional consistency, optimistic and pessimistic locking, idempotent request handling, and a real testing pyramid.

## Endpoints

- `POST /api/v1/transactions` — post a multi-entry transaction atomically. Sum of debits must equal sum of credits. Requests carry an `Idempotency-Key` header; duplicate keys return the original result without writing twice.
- `GET /api/v1/accounts/{id}/balance` — read an account's current balance.

## Tech stack

| Concern | Choice |
|---|---|
| Language / runtime | Java 21 |
| Framework | Spring Boot 3.3 |
| Build | Maven |
| Database | PostgreSQL 16 |
| Migrations | Flyway |
| ORM | Spring Data JPA + Hibernate |
| API docs | Springdoc OpenAPI (Swagger UI) |
| Observability | Spring Boot Actuator + Micrometer + Prometheus |
| Logging | Logback + JSON encoder + correlation IDs (MDC) |
| Testing | JUnit 5, AssertJ, Mockito, Testcontainers |
| Code quality | Spotless (Google Java Format), JaCoCo |
| CI | GitHub Actions |

No Lombok — getters and setters are written by hand on purpose.

## Running locally

Start Postgres:

```bash
docker compose up -d postgres
```

Run the app:

```bash
./mvnw spring-boot:run
```

(If you don't have a Maven wrapper yet, run `mvn -N wrapper:wrapper` once first, or just use `mvn` directly.)

Useful URLs:

- API: `http://localhost:8080/api/v1`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI spec: `http://localhost:8080/v3/api-docs`
- Actuator: `http://localhost:8080/actuator`
- Prometheus metrics: `http://localhost:8080/actuator/prometheus`

## Tests

```bash
./mvnw verify
```

Runs unit tests, slice tests, Testcontainers integration tests, Spotless format check, and produces a JaCoCo coverage report at `target/site/jacoco/index.html`.

## Architecture

Hexagonal (ports and adapters). Dependencies point inward: adapters depend on the application layer, the application layer depends on the domain, and the domain depends on nothing.

```
src/main/java/com/jpmc/ledger/
├── LedgerApplication.java
├── config/                          Spring beans, OpenAPI, etc.
├── domain/                          ← pure Java, no Spring, no JPA
│   ├── account/
│   ├── transaction/
│   └── shared/                      Money and other value objects
├── application/
│   ├── port/in/                     use case interfaces (driving ports)
│   ├── port/out/                    repository interfaces (driven ports)
│   └── service/                     use case implementations
└── adapter/
    ├── in/web/                      HTTP controllers, DTOs, error handling
    └── out/persistence/             JPA entities, Spring Data repos, mappers
```

See `docs/adr/` for the rationale behind each major decision.

## Status

Scaffolding only. Business logic is intentionally TODO — domain invariants, use case orchestration, and locking strategy are left for you to implement. Compile errors are your task list.

Things you'll write:

1. Domain invariants (`Transaction` enforces debits equal credits, currency consistency, etc.)
2. `Money` arithmetic and equality
3. Use case orchestration in `PostTransactionService` and `GetBalanceService`
4. The locking strategy on `AccountPersistenceAdapter` (start with `@Version` optimistic locking; add `SELECT ... FOR UPDATE` pessimistic locking as a second adapter when you want to compare)
5. The idempotency check in `PostTransactionService`
6. Controller method bodies (mostly delegation)
7. Tests at all three tiers — unit, slice (`@WebMvcTest`, `@DataJpaTest`), and Testcontainers integration including a concurrent-poster stress test

## Design decisions

See `docs/adr/`:

- [ADR-0001: Record architecture decisions](docs/adr/0001-record-architecture-decisions.md)
- [ADR-0002: Hexagonal architecture](docs/adr/0002-hexagonal-architecture.md)
- [ADR-0003: PostgreSQL for storage and idempotency](docs/adr/0003-postgresql-for-storage-and-idempotency.md)
- [ADR-0004: Double-entry invariants enforced in the domain](docs/adr/0004-double-entry-invariants-in-domain.md)
- [ADR-0005: No Lombok](docs/adr/0005-no-lombok.md)
