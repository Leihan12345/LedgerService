-- Idempotency keys live in the same database as the ledger so that recording
-- the key and posting the transaction are part of the SAME transaction.
--
-- request_hash protects against the case where a client reuses an idempotency
-- key for a DIFFERENT request body (which is a client bug); we detect it and
-- reject with 422 instead of silently returning a stale response.

CREATE TABLE idempotency_keys (
    key             VARCHAR(255) PRIMARY KEY,
    request_hash    VARCHAR(64)  NOT NULL,
    transaction_id  UUID         NOT NULL REFERENCES transactions (id),
    response_body   TEXT         NOT NULL,
    status_code     INTEGER      NOT NULL,
    created_at      TIMESTAMPTZ  NOT NULL DEFAULT now()
);
