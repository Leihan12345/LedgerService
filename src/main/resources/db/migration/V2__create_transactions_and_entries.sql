-- A transaction is the atomic unit of bookkeeping. It owns >=2 entries.
-- The "debits == credits" invariant is enforced by the domain aggregate at
-- write time. We do NOT enforce it with a deferred CHECK constraint — Postgres
-- supports them but the cross-row arithmetic is awkward to express and the
-- domain layer is where we want this rule to live (see ADR-0004).

CREATE TABLE transactions (
    id           UUID          PRIMARY KEY,
    description  VARCHAR(500),
    posted_at    TIMESTAMPTZ   NOT NULL DEFAULT now(),
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT now()
);

CREATE TABLE entries (
    id              UUID          PRIMARY KEY,
    transaction_id  UUID          NOT NULL REFERENCES transactions (id),
    account_id      UUID          NOT NULL REFERENCES accounts (id),
    entry_type      VARCHAR(10)   NOT NULL CHECK (entry_type IN ('DEBIT', 'CREDIT')),
    amount          NUMERIC(19, 4) NOT NULL CHECK (amount > 0),
    currency        CHAR(3)       NOT NULL,
    created_at      TIMESTAMPTZ   NOT NULL DEFAULT now()
);

CREATE INDEX idx_entries_transaction ON entries (transaction_id);
CREATE INDEX idx_entries_account     ON entries (account_id);
