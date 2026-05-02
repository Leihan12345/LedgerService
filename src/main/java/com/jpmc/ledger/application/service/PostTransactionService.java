package com.jpmc.ledger.application.service;

import com.jpmc.ledger.application.port.in.PostTransactionUseCase;
import com.jpmc.ledger.application.port.in.command.PostTransactionCommand;
import com.jpmc.ledger.application.port.out.AccountRepository;
import com.jpmc.ledger.application.port.out.IdempotencyStore;
import com.jpmc.ledger.application.port.out.TransactionRepository;
import com.jpmc.ledger.domain.transaction.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Use case: post a multi-entry transaction atomically with idempotency.
 *
 * <p>SCAFFOLD NOTE: the orchestration is up to you. A reasonable shape:
 *
 * <ol>
 *   <li>Hash the request and look up the idempotency key. If a record exists
 *       and the hash matches, return the stored result. If a record exists
 *       and the hash differs, throw a domain exception (the web layer maps
 *       this to HTTP 422).
 *   <li>Build a {@link Transaction} aggregate from the command — this is
 *       where the domain enforces debits-equal-credits and friends.
 *   <li>For each distinct account in the entries, load it for update
 *       ({@link AccountRepository#findByIdForUpdate}) and apply the signed
 *       amount.
 *   <li>Save the accounts and the transaction. Persist the idempotency
 *       record.
 *   <li>All of the above happens inside a single {@code @Transactional}
 *       block so the database commits atomically. On
 *       {@link org.springframework.dao.OptimisticLockingFailureException},
 *       decide whether to retry (bounded) or surface to the caller.
 * </ol>
 */
@Service
public class PostTransactionService implements PostTransactionUseCase {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final IdempotencyStore idempotencyStore;

    public PostTransactionService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            IdempotencyStore idempotencyStore) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.idempotencyStore = idempotencyStore;
    }

    @Override
    @Transactional
    public Transaction post(PostTransactionCommand command) {
        // TODO: implement per the steps above.
        throw new UnsupportedOperationException("TODO: implement PostTransactionService.post");
    }
}
