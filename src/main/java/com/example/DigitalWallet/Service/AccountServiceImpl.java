package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Model.AccountHistory;
import com.example.DigitalWallet.Model.Wallet;
import com.example.DigitalWallet.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountSerivce {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountHistory create(Wallet wallet, BigDecimal amount) {
        return accountRepository.create(wallet,amount);
    }

    @Override
    public BigDecimal addMoney(Wallet wallet, BigDecimal amount, BigDecimal finalBalance) {
        if(amount.compareTo(new BigDecimal(0.01)) < 1)
            throw new RuntimeException("Invalid Amount");
        return accountRepository.addMoney(wallet,amount,finalBalance);
    }

    @Override
    public List<AccountHistory> getStatementById(Integer id) {
        return accountRepository.get(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Boolean transfer(Wallet from, Wallet to, BigDecimal amount) {
        if(amount.compareTo(new BigDecimal(0.01)) < 1)
            throw new RuntimeException("Invalid Amount");
        BigDecimal fromBalance = from.getBalance().subtract(amount);
        if(fromBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Insufficient Balance");
        BigDecimal toBalance = to.getBalance().add(amount);
        accountRepository.transfer(from,to,amount,fromBalance,toBalance);
        return true;
    }
}
