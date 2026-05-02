package com.jpmc.ledger.application.port.in.command;

import com.jpmc.ledger.domain.account.AccountId;
import com.jpmc.ledger.domain.transaction.EntryType;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * Application-layer command for posting a transaction. Decoupled from the HTTP
 * request DTO so the use case can be invoked from any driving adapter (HTTP,
 * a CLI, a message consumer) without changes.
 *
 * <p>Records are a fine fit here: this object is immutable, value-equal, and
 * has no behaviour. Records ARE allowed in this codebase — the no-Lombok rule
 * does not apply to them (records are a language feature, not a library).
 */
public record PostTransactionCommand(
        String idempotencyKey,
        String description,
        List<EntryCommand> entries) {

    public record EntryCommand(AccountId accountId, EntryType type, BigDecimal amount, Currency currency) {}
}
