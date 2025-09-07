package com.example.walletservice.entity; // Убедись, что тут твой пакет!

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data // Аннотация Lombok, создает геттеры, сеттеры и т.д.
public class Wallet {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO; // Начальный баланс 0

    // Конструкторы
    public Wallet() {
    }

    public Wallet(UUID id) {
        this.id = id;
    }
}