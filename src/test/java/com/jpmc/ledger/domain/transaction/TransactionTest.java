package com.jpmc.ledger.domain.transaction;

import org.junit.jupiter.api.Test;

/**
 * Pure-domain unit tests. No Spring, no JPA, no database.
 *
 * <p>Suggested cases to write once the domain is implemented:
 *
 * <ul>
 *   <li>rejects a transaction with fewer than 2 entries
 *   <li>rejects a transaction where debits != credits
 *   <li>rejects a transaction with mixed currencies
 *   <li>rejects an entry with non-positive amount
 *   <li>accepts a balanced 2-entry transaction
 *   <li>accepts a balanced N-entry transaction (e.g., split debit across two
 *       accounts crediting one)
 * </ul>
 *
 * <p>This is the highest-leverage test layer — fast and exhaustive of the
 * rules. Spend disproportionate test budget here.
 */
class TransactionTest {

    @Test
    void todo_writeMe() {
        // TODO: implement once Transaction enforces invariants.
    }
}
