package com.jpmc.ledger.adapter.in.web.transaction;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * Body of {@code POST /api/v1/transactions}. The {@code Idempotency-Key}
 * arrives as an HTTP header rather than a body field — see
 * {@link TransactionController}.
 */
public class TransactionRequest {

    @Size(max = 500)
    private String description;

    @NotEmpty(message = "at least two entries are required")
    @Size(min = 2, message = "at least two entries are required")
    @Valid
    private List<EntryRequest> entries;

    public TransactionRequest() {
        // required for Jackson
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EntryRequest> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryRequest> entries) {
        this.entries = entries;
    }
}
