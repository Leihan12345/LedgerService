package com.jpmc.ledger.application.port.in;

import com.jpmc.ledger.domain.account.AccountId;
import com.jpmc.ledger.domain.shared.Money;

/**
 * Driving port: read an account's current balance.
 *
 * <p>This is intentionally simple. If a future use case needs balance-as-of a
 * point in time, add a separate port rather than overloading this one.
 */
public interface GetBalanceUseCase {

    Money getBalance(AccountId accountId);
}
