package com.jpmc.ledger.domain.shared;

import org.junit.jupiter.api.Test;

/**
 * Pure-domain unit tests for {@link Money}.
 *
 * <p>Suggested cases:
 *
 * <ul>
 *   <li>add/subtract within the same currency
 *   <li>add/subtract across currencies throws
 *   <li>negate flips sign
 *   <li>equality semantics — pick a rule (ignore scale? respect scale?) and
 *       lock it down here
 *   <li>BigDecimal precision is preserved across arithmetic
 * </ul>
 */
class MoneyTest {

    @Test
    void todo_writeMe() {
        // TODO
    }
}
