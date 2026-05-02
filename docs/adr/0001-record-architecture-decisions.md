# ADR-0001: Record architecture decisions

- Status: accepted
- Date: 2026-05-02

## Context

This is a portfolio project. The interesting work is not just the code that ships but the reasoning behind the choices made along the way. Without a written record, that reasoning evaporates the moment the conversation ends.

## Decision

We will use Architecture Decision Records (ADRs), one Markdown file per decision, kept under `docs/adr/`. Each ADR uses the template at `docs/adr/template.md`. ADRs are immutable once accepted; if a decision changes, a new ADR supersedes the old one and the old one's status is updated.

## Consequences

- Future readers (interviewers, future me) can see why the project looks the way it does.
- We commit to writing one paragraph of justification per non-trivial decision rather than relying on memory.
- Adds a small overhead per design choice. Worth it.

## Alternatives considered

- Inline comments in code. Rejected — they live with the code that exists, not with the decision about what to build.
- A single `DESIGN.md`. Rejected — decisions become tangled and history is lost.
