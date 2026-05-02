package com.jpmc.ledger.adapter.in.web.account;

import com.jpmc.ledger.application.port.in.GetBalanceUseCase;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP adapter for account reads.
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final GetBalanceUseCase getBalance;

    public AccountController(GetBalanceUseCase getBalance) {
        this.getBalance = getBalance;
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable("id") UUID id) {
        // TODO:
        //   1) Wrap UUID in AccountId.
        //   2) Call getBalance.getBalance(accountId).
        //   3) Map Money -> BalanceResponse.
        //   4) Return 200. (404 mapping happens via the global handler if the
        //      service throws AccountNotFoundException — define that exception
        //      in the domain.shared package when you need it.)
        throw new UnsupportedOperationException("TODO: AccountController.getBalance");
    }
}
