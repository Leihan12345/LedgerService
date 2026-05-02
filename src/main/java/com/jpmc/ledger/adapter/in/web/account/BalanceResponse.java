package com.jpmc.ledger.adapter.in.web.account;

import java.util.UUID;

/**
 * Response body for {@code GET /api/v1/accounts/{id}/balance}.
 */
public record BalanceResponse(UUID accountId, String balance, String currency) {}
