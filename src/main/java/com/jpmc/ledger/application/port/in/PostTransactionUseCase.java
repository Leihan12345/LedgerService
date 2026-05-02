package com.jpmc.ledger.application.port.in;

import com.jpmc.ledger.application.port.in.command.PostTransactionCommand;
import com.jpmc.ledger.domain.transaction.Transaction;

/**
 * Driving port: post a multi-entry transaction atomically.
 *
 * <p>Implementations must:
 *
 * <ul>
 *   <li>Be idempotent w.r.t. {@code command.idempotencyKey()} — replays return
 *       the original outcome without writing twice.
 *   <li>Be all-or-nothing — either every entry is persisted and every account
 *       balance updated, or nothing is.
 *   <li>Defend against concurrent updates to the same account (locking
 *       strategy is an adapter detail).
 * </ul>
 */
public interface PostTransactionUseCase {

    Transaction post(PostTransactionCommand command);
}
