package com.example.walletservice.repository;

import com.example.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    // Важно! Метод для поиска с пессимистичной блокировкой.
    // Это решает проблему параллельных запросов (race condition).
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM Wallet w WHERE w.id = :id")
    Optional<Wallet> findByIdWithLock(@Param("id") UUID id);

    // Стандартный метод тоже оставляем, он пригодится для GET-запроса, где блокировка не нужна.
    Optional<Wallet> findById(UUID id);
}