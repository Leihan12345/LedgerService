package com.jpmc.ledger.domain.shared;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Immutable money value object.
 *
 * <p>Money is amount + currency. Amount is always {@link BigDecimal} — never
 * {@code double} or {@code float}, which lose precision in ways that quietly
 * break ledgers. This class is part of the pure domain layer and must not
 * import Spring or JPA.
 *
 * <p>SCAFFOLD NOTE: fields and the trivial constructor are written for you so
 * the rest of the project compiles. Behaviour ({@code add}, {@code subtract},
 * {@code negate}, equality, etc.) is intentionally TODO — write it yourself.
 */
public final class Money {

    private final BigDecimal amount;
    private final Currency currency;

    // This is the constructor
    public Money(BigDecimal amount, Currency currency) {
        this.amount = Objects.requireNonNull(amount, "amount must not be null");
        this.currency = Objects.requireNonNull(currency, "currency must not be null");
    }

    // Getter for amount
    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("other must be non-null");
        }
        if (!other.getCurrency().equals(currency)) {
            throw new IllegalArgumentException("Cannot add Money with different currencies: " + this.currency + " vs " + other.currency);
        }
        BigDecimal newAmount = other.getAmount().add(amount);
        return new Money(newAmount, currency);
    }

    public Money subtract(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("other must be non-null");
        }
        if (!other.getCurrency().equals(currency)) {
            throw new IllegalArgumentException("Cannot subtract Money with different currencies: " + this.currency + " vs " + other.currency);
        }
        BigDecimal newAmount = amount.subtract(other.getAmount());
        return new Money(newAmount, currency);
    }

    public Money negate() {
        return new Money(amount.negate(), currency);
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isZero() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isNegative() {
        return this.amount.compareTo(BigDecimal.ZERO) < 0;
    }

    // Override, which allows you to
    @Override
    public boolean equals(Object o) {
        // since this overrides the default implementation of equals in the object class
        if (!(o instanceof Money other)) {
            return false;
        }
        return this.amount.compareTo(other.getAmount()) == 0 && this.currency.equals(other.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), currency);
    }



    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " + amount.toPlainString();
    }
}
