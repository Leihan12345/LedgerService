package com.jpmc.ledger.adapter.in.web.transaction;

import com.jpmc.ledger.domain.transaction.EntryType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * One line of a {@link TransactionRequest}. HTTP-shape DTO — kept separate
 * from the application-layer command so the wire format and the use case
 * vocabulary can evolve independently.
 */
public class EntryRequest {

    @NotNull
    private UUID accountId;

    @NotNull
    private EntryType type;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "amount must be > 0")
    private BigDecimal amount;

    @NotNull
    @Size(min = 3, max = 3, message = "currency must be a 3-letter ISO code")
    private String currency;

    public EntryRequest() {
        // required for Jackson deserialization
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
