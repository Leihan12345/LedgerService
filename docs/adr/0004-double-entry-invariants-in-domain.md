# ADR-0004: Double-entry invariants enforced in the domain

- Status: accepted
- Date: 2026-05-02

## Context

A double-entry ledger has hard invariants:

1. Every transaction has at least two entries.
2. The sum of debits equals the sum of credits.
3. All entries within a transaction share a single currency (or, if cross-currency, are explicitly converted).
4. Entry amounts are strictly positive — sign is carried by `EntryType` (DEBIT or CREDIT).

Violating any of these silently corrupts the books. Database constraints can catch some of these (positive amounts, currency length) but not the cross-entry sum check.

## Decision

Invariants are enforced inside the `Transaction` aggregate at construction time. A `Transaction` cannot exist in an invalid state — the constructor (or static factory) rejects bad input by throwing a domain exception. Database `CHECK` constraints are kept as a defence-in-depth backstop.

`Money` is an immutable value object using `BigDecimal`. We never use `double` or `float` for monetary amounts.

## Consequences

- Domain tests can verify invariants without spinning up a database.
- Controllers and services don't need to remember to validate — if you can construct a `Transaction`, it's valid.
- We pay a small mapping cost: the JPA entity is a separate class from the domain aggregate, and a mapper translates between them.

## Alternatives considered

- **Validate in the service layer**. Rejected: invariants leak across many call sites and inevitably get missed.
- **Use `@Entity` as the domain object**. Rejected: ties invariants to JPA lifecycle (no-arg constructor, mutable fields for Hibernate proxies) which makes them hard to enforce cleanly.
