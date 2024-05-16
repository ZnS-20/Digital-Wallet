package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Model.Wallet;

import java.math.BigDecimal;
import java.util.Optional;

public interface WalletService {

    Wallet createWallet(String name);

    Optional<Wallet> getWallet(Integer walletId);

    void updateBalance(Integer walletId, BigDecimal finalBalance);
}
