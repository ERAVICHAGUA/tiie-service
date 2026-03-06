package com.engineai.tiie.infrastructure.controller;

import java.math.BigDecimal;

public record CreateTransactionRequest(
        Long userId,
        String type,
        BigDecimal amount,
        String currency,
        String rawDescription,
        String merchantRaw,
        String occurredAt
) {
}