package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Model.AccountHistory;
import com.example.DigitalWallet.Model.Wallet;
import com.example.DigitalWallet.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountSerivce accountSerivce;

    @Override
    public Wallet createWallet(String name) {
        var wallet = walletRepository.create(name);
        var account = accountSerivce.create(wallet, new BigDecimal(0));
        return wallet;
    }

    @Override
    public Optional<Wallet> getWallet(Integer walletId) {
        return walletRepository.getWalletById(walletId);
    }

    @Override
    public void updateBalance(Integer walletId, BigDecimal finalBalance) {
        walletRepository.updateById(walletId,finalBalance);
    }
}
