package com.jpmc.ledger.domain.shared;

/**
 * Base class for exceptions thrown by the domain layer when an invariant is
 * violated. The web layer translates these into HTTP problem responses; the
 * domain layer itself stays free of HTTP concerns.
 *
 * <p>Subclass this for specific failure modes (e.g.,
 * {@code DebitsDoNotEqualCreditsException}, {@code CurrencyMismatchException},
 * {@code AccountNotFoundException}) so callers can catch precisely what they
 * intend to handle.
 */
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
