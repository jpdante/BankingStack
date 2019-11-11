package com.ellisiumx.bankingstack.model;

import java.util.Date;

public class Account {
    private int accountID;
    private Client client;
    private Date creationDate;
    protected double balance;

    public Account(int accountID, Client client, Date creationDate) {
        this(accountID, client, creationDate, 0d);
    }

    public Account(int accountID, Client client, Date creationDate, double balance) {
        this.accountID = accountID;
        this.client = client;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public Client getClient() { return this.client; }

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
