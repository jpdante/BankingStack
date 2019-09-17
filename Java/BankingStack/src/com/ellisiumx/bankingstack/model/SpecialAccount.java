package com.ellisiumx.bankingstack.model;

import java.util.Date;

public class SpecialAccount extends Account {
    private double accountLimit;

    public SpecialAccount(int accountID, Client user, Date creationDate, double accountLimit) {
        this(accountID, user, creationDate, 0d, accountLimit);
    }

    public SpecialAccount(int accountID, Client user, Date creationDate, double balance, double accountLimit) {
        super(accountID, user, creationDate, balance);
        this.accountLimit = accountLimit;
    }

    public double getAccountLimit() { return this.accountLimit; }

    public boolean setAccountLimit(double newAccountLimit) {
        if(newAccountLimit < 0) return false;
        this.accountLimit = newAccountLimit;
        return true;
    }

    public boolean removeFunds(double amount) {
        if(this.getBalance() - amount < this.accountLimit) return false;
        this.balance = this.getBalance() - amount;
        return true;
    }
}
