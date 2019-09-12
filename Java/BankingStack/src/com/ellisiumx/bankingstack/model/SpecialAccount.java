package com.ellisiumx.bankingstack.model;

import java.time.LocalDateTime;

public class SpecialAccount extends Account {
    private double accountLimit;

    public SpecialAccount(int accountID, User user, LocalDateTime creationDate, double accountLimit) {
        super(accountID, user, creationDate);
        this.accountLimit = accountLimit;
    }

    public boolean removeFunds(double amount) {
        if(this.getBalance() - amount < this.accountLimit) return false;
        return this.setBalance(this.getBalance() - amount);
    }
}
