package com.jpmc.ledger.domain.transaction;

import java.util.UUID;

/**
 * Strongly-typed identifier for a Transaction.
 *
 * <p>SCAFFOLD NOTE: model this on {@link com.jpmc.ledger.domain.account.AccountId}.
 * Equals/hashCode/toString and the constructor are left for you to write.
 */
public final class TransactionId {

    private final UUID value;

    public TransactionId(UUID value) {
        // TODO: null check
        this.value = value;
    }

    public static TransactionId newId() {
        return new TransactionId(UUID.randomUUID());
    }

    public static TransactionId of(UUID value) {
        return new TransactionId(value);
    }

    public UUID value() {
        return value;
    }

    // TODO: equals, hashCode, toString
}
