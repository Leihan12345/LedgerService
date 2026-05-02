# ADR-0002: Hexagonal architecture (ports and adapters)

- Status: accepted
- Date: 2026-05-02

## Context

The service has a real domain model — a double-entry ledger with invariants like "debits must equal credits" and "an account's balance is the sum of its entries." That kind of logic is easy to scatter across controllers and services in a typical layered Spring Boot app, where `@Entity` classes double as domain objects and the "service" layer becomes a procedural pipe.

## Decision

We organize the codebase as ports and adapters:

- `domain/` — pure Java, no Spring, no JPA. Aggregates and value objects only. Enforces invariants.
- `application/port/in/` — use case interfaces (driving ports).
- `application/port/out/` — repository and external-system interfaces (driven ports).
- `application/service/` — use case implementations. Depend only on the domain and on ports.
- `adapter/in/web/` — Spring MVC controllers and DTOs. Depend on `port.in`.
- `adapter/out/persistence/` — JPA entities and Spring Data repositories. Implement `port.out`.

Dependencies point inward: adapters → application → domain. The domain depends on nothing.

## Consequences

- The domain model can be unit-tested without Spring.
- Locking strategy lives in a single adapter class — swapping optimistic for pessimistic locking is a one-file change.
- We accept the cost of a mapping layer between domain objects and JPA entities.
- More files than a flat layered project. The trade-off is worth it for a project that's meant to demonstrate architectural fluency.

## Alternatives considered

- **Package-by-layer** (`controller/`, `service/`, `repository/`). Tutorial-grade. Rejected: makes the domain invisible.
- **Package-by-feature** (`account/`, `transaction/` each with their own controller/service/repo). Solid choice. Rejected because we specifically want the domain isolation that hexagonal gives us.
