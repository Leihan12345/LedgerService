package com.jpmc.ledger.domain.account;

import com.jpmc.ledger.domain.shared.Money;

/**
 * Account aggregate root.
 *
 * <p>An Account has an identity, a name, a currency, a current balance, and a
 * version (used by the persistence layer for optimistic locking). The balance
 * is a derived value — it equals the sum of all signed entries posted against
 * this account — but we cache it on the row for O(1) reads.
 *
 * <p>SCAFFOLD NOTE: this class is intentionally minimal. You decide what
 * mutating operations belong on the aggregate (e.g., {@code applyDebit},
 * {@code applyCredit}) and what stays in the application service.
 */
public class Account {

    private final AccountId id;
    private String name;
    private Money balance;
    private long version;

    public Account(AccountId id, String name, Money balance, long version) {
        // TODO: validate non-null id/name/balance.
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.version = version;
    }

    public AccountId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // TODO: validate (non-empty, length cap).
        this.name = name;
    }

    public Money getBalance() {
        return balance;
    }

    public long getVersion() {
        return version;
    }

    // TODO: void applyDebit(Money amount)  — assert currency match, mutate balance
    // TODO: void applyCredit(Money amount)
    //
    // Decide: do you want to allow negative balances? Liability accounts and
    // contra-asset accounts can legitimately go negative; cash accounts
    // typically should not. This is a domain rule, not a database constraint.
}
