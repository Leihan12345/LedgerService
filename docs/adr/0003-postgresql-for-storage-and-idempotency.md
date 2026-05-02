# ADR-0003: PostgreSQL for storage and idempotency

- Status: accepted
- Date: 2026-05-02

## Context

The ledger has two storage problems: persisting accounts and transactions atomically, and deduplicating client requests by idempotency key. Both need transactional guarantees. We considered using Redis as a sidecar for idempotency.

## Decision

PostgreSQL 16 is the single source of truth. The idempotency table lives alongside the ledger tables and participates in the same database transaction as the write that consumes the key.

## Consequences

- Posting a transaction and recording the idempotency key are atomic — there is no window where the key is recorded but the transaction is not.
- One operational dependency, not two.
- Postgres gives us real `SERIALIZABLE` isolation, `SELECT ... FOR UPDATE` row locking, and advisory locks if we need them. H2 and similar in-memory DBs diverge on locking semantics; we don't use them, even in tests — Testcontainers spins up real Postgres.
- Throughput ceiling is whatever a single Postgres instance can handle. For a portfolio project this is fine; in production we'd revisit at scale.

## Alternatives considered

- **Redis for idempotency**. Rejected: introduces a second system that can drift from the database, and we'd need a two-phase commit dance to keep them consistent.
- **H2 / HSQLDB for tests**. Rejected: locking and isolation differ from production, masking concurrency bugs. Testcontainers Postgres gives us production parity.
