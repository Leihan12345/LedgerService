package com.jpmc.ledger.domain.account;

import java.util.Objects;
import java.util.UUID;

/**
 * Strongly-typed identifier for an Account.
 *
 * <p>Why a wrapper instead of using {@link UUID} directly? It prevents
 * accidentally passing a {@code TransactionId} where an {@code AccountId} is
 * expected — the compiler catches the mistake instead of the database.
 *
 * <p>This is a complete reference implementation of a value-object identifier.
 * Use it as a model when writing {@link com.jpmc.ledger.domain.transaction.TransactionId}.
 */
public final class AccountId {

    private final UUID value;

    public AccountId(UUID value) {
        this.value = Objects.requireNonNull(value, "AccountId value must not be null");
    }

    public static AccountId newId() {
        return new AccountId(UUID.randomUUID());
    }

    public static AccountId of(UUID value) {
        return new AccountId(value);
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountId other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
