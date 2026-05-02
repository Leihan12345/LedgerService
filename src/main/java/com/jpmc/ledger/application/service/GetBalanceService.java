package com.jpmc.ledger.application.service;

import com.jpmc.ledger.application.port.in.GetBalanceUseCase;
import com.jpmc.ledger.application.port.out.AccountRepository;
import com.jpmc.ledger.domain.account.AccountId;
import com.jpmc.ledger.domain.shared.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Use case: read an account's balance.
 *
 * <p>Read-only — annotate with {@code @Transactional(readOnly = true)} when
 * you implement the body so Hibernate can skip the dirty-checking flush.
 */
@Service
public class GetBalanceService implements GetBalanceUseCase {

    private final AccountRepository accountRepository;

    public GetBalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Money getBalance(AccountId accountId) {
        // TODO: load the account, throw a domain exception if missing,
        //       return its balance.
        throw new UnsupportedOperationException("TODO: implement GetBalanceService.getBalance");
    }
}
