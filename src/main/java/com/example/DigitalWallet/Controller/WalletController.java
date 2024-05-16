package com.example.DigitalWallet.Controller;

import com.example.DigitalWallet.Model.Wallet;
import com.example.DigitalWallet.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;
    @RequestMapping(value = "/{accountName}", method = RequestMethod.POST)
    ResponseEntity<String> create(@PathVariable String accountName){
        if(accountName == null)
            throw new RuntimeException("Name cannot be null");
        var wallet = walletService.createWallet(accountName);
        return new ResponseEntity<String>("Your account is created with the following details "+wallet.toString(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    ResponseEntity<Wallet> getById(@PathVariable Integer id){
        return new ResponseEntity<>(walletService.getWallet(id).get(),HttpStatus.OK);
    }

}
