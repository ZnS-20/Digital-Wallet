package com.example.DigitalWallet.Repository;

import com.example.DigitalWallet.Model.AccountHistory;
import com.example.DigitalWallet.Model.Wallet;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {

    private static Map<Integer, List<AccountHistory>> accountHashMap;

    AccountRepository(){
        accountHashMap = new ConcurrentHashMap<>();
    }

    public AccountHistory create(Wallet wallet, BigDecimal amount){
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setWalletId(wallet.getId());
        accountHistory.setBalance(amount);
        accountHistory.setCredit(amount);
        accountHistory.setDepositedBy(wallet.getId());
        accountHistory.setCreateTime(LocalDateTime.now());
        accountHashMap.put(wallet.getId(), new ArrayList<AccountHistory>(Arrays.asList(accountHistory)));
        return accountHistory;
    }

    public Optional<List<AccountHistory>> get(Integer userId){
        var statement = accountHashMap.get(userId);
        if(statement == null)
                return Optional.empty();
        return Optional.of(statement);
    }

    public void transfer(Wallet from, Wallet to, BigDecimal amount,BigDecimal fromBalance,BigDecimal toBalance){
        debitAmount(from,amount,fromBalance,to.getId());
        creditAmount(to,amount,toBalance,from.getId());
    }

    private void debitAmount(Wallet from, BigDecimal debitedAmount, BigDecimal balance, Integer creditedTo) {
        var accountHistoryList = accountHashMap.get(from.getId());
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setWalletId(from.getId());
        accountHistory.setBalance(balance);
        accountHistory.setDeposit(debitedAmount);
        accountHistory.setCreditTo(creditedTo);
        accountHistoryList.add(accountHistory);
        accountHashMap.put(from.getId(),accountHistoryList);
    }

    private void creditAmount(Wallet to, BigDecimal creditedAmount, BigDecimal balance, Integer debitedBy) {
        var accountHistoryList = accountHashMap.get(to.getId());
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setWalletId(to.getId());
        accountHistory.setBalance(balance);
        accountHistory.setCredit(creditedAmount);
        accountHistory.setDepositedBy(debitedBy);
        accountHistoryList.add(accountHistory);
        accountHashMap.put(to.getId(),accountHistoryList);
    }


    public BigDecimal addMoney(Wallet wallet, BigDecimal amount, BigDecimal finalBalance) {
        if(accountHashMap.get(wallet.getId()) == null)
            create(wallet,amount);
        else{
            var accountHistoryList = accountHashMap.get(wallet.getId());
            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setWalletId(wallet.getId());
            accountHistory.setBalance(finalBalance);
            accountHistory.setCredit(amount);
            accountHistory.setDepositedBy(wallet.getId());
            accountHistory.setCreateTime(LocalDateTime.now());
            accountHistoryList.add(accountHistory);
            accountHashMap.put(wallet.getId(), accountHistoryList);
        }
        return finalBalance;
    }
}
