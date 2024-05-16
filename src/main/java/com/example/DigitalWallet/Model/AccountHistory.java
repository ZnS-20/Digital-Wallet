package com.example.DigitalWallet.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountHistory {

    private int walletId;

    private BigDecimal deposit;

    private BigDecimal credit;

    private int depositedBy;

    private int creditTo;

    private BigDecimal balance;

    private LocalDateTime createTime;

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public int getDepositedBy() {
        return depositedBy;
    }

    public void setDepositedBy(int depositedBy) {
        this.depositedBy = depositedBy;
    }

    public int getCreditTo() {
        return creditTo;
    }

    public void setCreditTo(int creditTo) {
        this.creditTo = creditTo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
