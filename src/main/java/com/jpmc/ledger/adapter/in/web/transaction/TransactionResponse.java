package com.jpmc.ledger.adapter.in.web.transaction;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Response body for {@code POST /api/v1/transactions}.
 *
 * <p>Records are fine for response DTOs — they're immutable, value-equal,
 * and Jackson serializes them out of the box.
 */
public record TransactionResponse(
        UUID id,
        String description,
        Instant postedAt,
        List<EntryResponse> entries) {

    public record EntryResponse(UUID accountId, String type, String amount, String currency) {}
}
