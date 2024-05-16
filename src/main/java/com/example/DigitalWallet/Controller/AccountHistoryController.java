package com.example.DigitalWallet.Controller;

import com.example.DigitalWallet.Model.AccountHistory;
import com.example.DigitalWallet.Model.Wallet;
import com.example.DigitalWallet.Repository.AccountRepository;
import com.example.DigitalWallet.Service.AccountSerivce;
import com.example.DigitalWallet.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/account-history")
public class AccountHistoryController {

    @Autowired
    private AccountSerivce accountSerivce;

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    ResponseEntity<String> addMoney(@RequestParam Integer walletId, @RequestParam BigDecimal amount){
        Optional<Wallet> wallet = walletService.getWallet(walletId);
        if(wallet.isEmpty()){
            throw new RuntimeException("No Wallet Found");
        }
        BigDecimal finalBalance = wallet.get().getBalance().add(amount);
        var addMoney = accountSerivce.addMoney(wallet.get(),amount,finalBalance);
        walletService.updateBalance(walletId, finalBalance);
        return new ResponseEntity<>("Money Added Successfully, Balance "+addMoney, HttpStatus.OK);
    }

    @RequestMapping(value = "/statement/{id}", method = RequestMethod.GET)
    ResponseEntity<List<AccountHistory>> getStatementById(@PathVariable Integer id){
        return ResponseEntity.ok(accountSerivce.getStatementById(id));
    }

    @RequestMapping(value = "/transfer/{from}/{to}/{amount}", method = RequestMethod.PUT)
    ResponseEntity<String> transferMoney(@PathVariable Integer from, @PathVariable Integer to, @PathVariable BigDecimal amount){
        Wallet fromWallet = walletService.getWallet(from).orElseThrow(RuntimeException::new);
        Wallet toWallet = walletService.getWallet(to).orElseThrow(RuntimeException::new);
        var transferred = accountSerivce.transfer(fromWallet,toWallet,amount);
        walletService.updateBalance(from,fromWallet.getBalance().subtract(amount));
        walletService.updateBalance(to,toWallet.getBalance().add(amount));
        return ResponseEntity.ok( transferred ? "Success" : "Failure");
    }

}
