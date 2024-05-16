package com.example.DigitalWallet.Repository;

import com.example.DigitalWallet.Model.Wallet;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WalletRepository {

    private static Map<Integer, Wallet> walletHashMap = new ConcurrentHashMap<>();
    private static Integer accountId = 0;

    private Integer accountIdGenerator(){
        accountId = accountId +1;
        return accountId;
    }
    public com.example.DigitalWallet.Model.Wallet create(String accountName){
        int accountId = accountIdGenerator();
        com.example.DigitalWallet.Model.Wallet wallet = new com.example.DigitalWallet.Model.Wallet();
        wallet.setId(accountId);
        wallet.setName(accountName);
        wallet.setBalance(new BigDecimal(0));
        walletHashMap.put(accountId,wallet);
        return wallet;
    }

    public Optional<Wallet> getWalletById(Integer walletId) {
        return Optional.ofNullable(walletHashMap.get(walletId));
    }

    public void updateById(Integer walletId, BigDecimal finalBalance) {
        var wallet = walletHashMap.get(walletId);
        if(wallet == null)
                throw new RuntimeException("Wallet not found");
        wallet.setBalance(finalBalance);
        walletHashMap.put(walletId,wallet);
    }
}
