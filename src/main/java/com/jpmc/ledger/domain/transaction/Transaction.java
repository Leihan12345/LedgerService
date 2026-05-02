package com.jpmc.ledger.domain.transaction;

import com.jpmc.ledger.domain.shared.DomainException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * Transaction aggregate root.
 *
 * <p>A transaction is a set of {@link Entry} lines that, together, must
 * satisfy the double-entry invariants:
 *
 * <ol>
 *   <li>At least two entries.
 *   <li>Sum of debits equals sum of credits (within a currency).
 *   <li>All entries share a single currency. (For multi-currency support, add
 *       an explicit FX entry — don't loosen this rule.)
 *   <li>All entry amounts are strictly positive.
 * </ol>
 *
 * <p>Invariants are enforced at construction time. If you can hold a
 * {@code Transaction} reference, it is valid by construction.
 */
public final class Transaction {

    private final TransactionId id;
    private final String description;
    private final List<Entry> entries;
    private final Instant postedAt;

    public Transaction(TransactionId id, String description, List<Entry> entries, Instant postedAt) {
        // TODO: null checks
        validateInvariants(entries);
        this.id = id;
        this.description = description;
        this.entries = List.copyOf(entries); // defensive immutable copy
        this.postedAt = postedAt;
    }

    private static void validateInvariants(List<Entry> entries) {
        // TODO: enforce
        //   1) entries.size() >= 2
        //   2) sum of debit amounts == sum of credit amounts
        //   3) all entries share the same currency
        //   4) every entry's amount is strictly positive
        // Throw a descriptive subclass of DomainException on failure.
        if (entries == null || entries.size() < 2) {
            throw new DomainException("A transaction must have at least two entries");
        }
        // TODO: rest of the rules
    }

    public TransactionId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Entry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public Instant getPostedAt() {
        return postedAt;
    }
}
