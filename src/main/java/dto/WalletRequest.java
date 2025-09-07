package com.example.walletservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WalletRequest {

    @NotNull(message = "walletId is mandatory")
    private UUID walletId;

    @NotNull(message = "operationType is mandatory")
    private OperationType operationType;

    @NotNull(message = "amount is mandatory")
    @Positive(message = "amount must be positive")
    private BigDecimal amount;
}