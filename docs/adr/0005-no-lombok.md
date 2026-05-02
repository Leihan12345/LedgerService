# ADR-0005: No Lombok

- Status: accepted
- Date: 2026-05-02

## Context

Lombok eliminates boilerplate (getters, setters, equals/hashCode, builders) via annotation processing. Many Spring Boot projects depend on it. For this project we want explicit Java code so that the author internalizes the JavaBeans conventions, the contract of `equals`/`hashCode`, and the cost of mutability.

## Decision

No Lombok dependency. Getters, setters, constructors, `equals`, `hashCode`, and `toString` are written by hand. Where appropriate, Java records are used (records are a language feature, not a library).

## Consequences

- More lines of code, especially in entities and DTOs.
- Zero magic — what you read is what runs. No "where did this method come from?" moments.
- New contributors don't need to install the Lombok IDE plugin.
- Refactoring across getters/setters is the IDE's job and works fine.

## Alternatives considered

- **Lombok**. Rejected for the learning reason above. Reconsider if the project ships and the boilerplate becomes a real cost.
- **Records everywhere**. Records work great for immutable value objects and DTOs but not for JPA entities (which need mutable state and a no-arg constructor) or for domain aggregates that need behaviour. We use records selectively.
