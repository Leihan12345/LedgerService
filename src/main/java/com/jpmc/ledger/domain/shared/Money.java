package com.jpmc.ledger.domain.shared;

import java.math.BigDecimal;
import java.util.Currency;

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

    public Money(BigDecimal amount, Currency currency) {
        // TODO: validate amount and currency are non-null.
        // TODO: decide on a canonical scale (e.g., currency.getDefaultFractionDigits())
        //       and either reject mismatched scales or normalize.
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    // TODO: Money add(Money other)         — same currency only, throw otherwise
    // TODO: Money subtract(Money other)
    // TODO: Money negate()
    // TODO: boolean isPositive() / isZero() / isNegative()
    //
    // TODO: equals/hashCode — value objects are equal by value, not identity.
    //       Be careful with BigDecimal equality (BigDecimal.equals considers scale;
    //       compareTo == 0 does not). Pick one and document why.
    // TODO: toString — useful for logs and for failed-assertion messages in tests.
}
