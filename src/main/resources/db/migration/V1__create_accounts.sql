-- Accounts hold the running balance plus a JPA optimistic-locking version.
-- Balance is denormalized for O(1) reads; it is recomputable from `entries`.
-- We keep balance and version on the same row so a concurrent update collides
-- on @Version (optimistic) or row-level lock (pessimistic), depending on which
-- adapter implementation the application is wired to.

CREATE TABLE accounts (
    id          UUID         PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    currency    CHAR(3)      NOT NULL,
    balance     NUMERIC(19, 4) NOT NULL DEFAULT 0,
    version     BIGINT       NOT NULL DEFAULT 0,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE INDEX idx_accounts_currency ON accounts (currency);
