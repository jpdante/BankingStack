package com.ellisiumx.bankingstack.model;

import java.util.Date;

public class Account {
    private int accountID;
    private Client user;
    private Date creationDate;
    protected double balance;

    public Account(int accountID, Client user, Date creationDate) {
        this(accountID, user, creationDate, 0d);
    }

    public Account(int accountID, Client user, Date creationDate, double balance) {
        this.accountID = accountID;
        this.user = user;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public Client getUser() { return this.user; }

    public Date getCreationDate() { return this.creationDate; }

    public double getBalance() {
        return this.balance;
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
