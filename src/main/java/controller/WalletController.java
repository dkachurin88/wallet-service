package com.example.walletservice.controller;

import com.example.walletservice.dto.WalletRequest;
import com.example.walletservice.entity.Wallet;
import com.example.walletservice.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> processTransaction(@Valid @RequestBody WalletRequest request) {
        Wallet updatedWallet = walletService.modifyBalance(request);
        return ResponseEntity.ok(updatedWallet);
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<Wallet> getWalletBalance(@PathVariable UUID walletId) {
        Wallet wallet = walletService.getBalance(walletId);
        return ResponseEntity.ok(wallet);
    }
}