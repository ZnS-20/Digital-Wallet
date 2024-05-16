package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Model.AccountHistory;
import com.example.DigitalWallet.Model.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface AccountSerivce {

    AccountHistory create(Wallet wallet, BigDecimal amount);

    BigDecimal addMoney(Wallet wallet, BigDecimal amount, BigDecimal finalBalance);

    List<AccountHistory> getStatementById(Integer id);

    Boolean transfer(Wallet from, Wallet to, BigDecimal amount);
}
