package com.jpmc.ledger.adapter.in.web.transaction;

import com.jpmc.ledger.application.port.in.PostTransactionUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP adapter for posting transactions. Thin — translates DTO to command,
 * calls the use case, translates the result back. No business logic here.
 */
@RestController
@RequestMapping("/api/v1/transactions")
@Validated
public class TransactionController {

    private final PostTransactionUseCase postTransaction;

    public TransactionController(PostTransactionUseCase postTransaction) {
        this.postTransaction = postTransaction;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> post(
            @RequestHeader("Idempotency-Key") @NotBlank String idempotencyKey,
            @Valid @RequestBody TransactionRequest request) {
        // TODO:
        //   1) Map TransactionRequest + idempotencyKey -> PostTransactionCommand
        //      (convert UUID -> AccountId, BigDecimal/currency -> domain values).
        //   2) Call postTransaction.post(command).
        //   3) Map the returned domain Transaction -> TransactionResponse.
        //   4) Return 201 Created with the response body. (Replays via the
        //      idempotency path should still return 201 with the original body.)
        throw new UnsupportedOperationException("TODO: TransactionController.post");
    }
}
