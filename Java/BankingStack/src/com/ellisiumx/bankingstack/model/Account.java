package com.ellisiumx.bankingstack.model;

import com.ellisiumx.bankingstack.utils.VerificationUtils;

import java.time.LocalDateTime;

public class Account {
    private int accountID;
    private User user;
    private LocalDateTime creationDate;
    private double balance;

    public Account(int accountID, User user, LocalDateTime creationDate) {
        this.accountID = accountID;
        this.user = user;
        this.creationDate = creationDate;
        this.balance = 0;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public User getUser() { return this.user; }

    public LocalDateTime getCreationDate() { return this.creationDate; }

    public double getBalance() {
        return this.balance;
    }

    public boolean setBalance(double balance) {
        this.balance = balance;
        return true;
    }

    public boolean addFunds(double amount) {
        if(amount <= 0.0d) return false;
        this.balance += amount;
        return true;
    }

    public boolean removeFunds(double amount) {
        if(amount <= 0.0d) return false;
        if(this.balance < amount) return false;
        this.balance -= amount;
        return true;
    }
}
