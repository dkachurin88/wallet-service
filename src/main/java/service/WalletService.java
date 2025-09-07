package com.example.walletservice.service;

import com.example.walletservice.dto.OperationType;
import com.example.walletservice.dto.WalletRequest;
import com.example.walletservice.entity.Wallet;
import com.example.walletservice.repository.WalletRepository;
import com.example.walletservice.exception.InsufficientFundsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    public Wallet modifyBalance(WalletRequest request) {
        Wallet wallet = walletRepository.findByIdWithLock(request.getWalletId())
                .orElseGet(() -> createNewWallet(request.getWalletId()));

        BigDecimal amount = request.getAmount();

        if (request.getOperationType() == OperationType.DEPOSIT) {
            wallet.setBalance(wallet.getBalance().add(amount));
        } else if (request.getOperationType() == OperationType.WITHDRAW) {
            if (wallet.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds in wallet " + request.getWalletId());
            }
            wallet.setBalance(wallet.getBalance().subtract(amount));
        }
        return walletRepository.save(wallet);
    }

    public Wallet getBalance(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with id: " + walletId));
    }

    private Wallet createNewWallet(UUID walletId) {
        Wallet newWallet = new Wallet(walletId);
        return walletRepository.save(newWallet);
    }
}