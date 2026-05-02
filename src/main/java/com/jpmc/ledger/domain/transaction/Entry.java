package com.jpmc.ledger.domain.transaction;

import com.jpmc.ledger.domain.account.AccountId;
import com.jpmc.ledger.domain.shared.Money;

/**
 * A single line within a {@link Transaction}: an account, a type
 * (DEBIT/CREDIT), and a strictly-positive amount.
 *
 * <p>An entry is immutable. Once created it does not change — to "amend" a
 * transaction you post a reversing transaction.
 */
public final class Entry {

    private final AccountId accountId;
    private final EntryType type;
    private final Money amount;

    public Entry(AccountId accountId, EntryType type, Money amount) {
        // TODO: null checks
        // TODO: amount must be strictly positive — reject zero and negative
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public EntryType getType() {
        return type;
    }

    public Money getAmount() {
        return amount;
    }

    /**
     * Convenience: signed magnitude. Debits are positive, credits negative
     * (or vice-versa — your call; pick a convention and stick to it).
     */
    public Money signedAmount() {
        // TODO: implement based on `type` and Money.negate()
        throw new UnsupportedOperationException("TODO");
    }
}
