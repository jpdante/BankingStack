package com.ellisiumx.bankingstack.model;

import com.ellisiumx.bankingstack.utils.VerificationUtils;

public class Account {
    private int accountID;
    private int branchID;
    private String firstName;
    private String lastName;
    private String phone;
    private String cpf;
    private String password;
    private double balance;

    public Account(int accountID, int branchID, String firstName, String lastName, String phone, String cpf, String password) {
        this.accountID = accountID;
        this.branchID = branchID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.cpf = cpf;
        this.password = password;
        this.balance = 0;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public int getBranchID() {
        return this.branchID;
    }

    public boolean setBranchID(int newID) {
        if(newID <= 0) return false;
        this.branchID = newID;
        return true;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public boolean setFirstName(String newFirstName) {
        this.firstName = newFirstName;
        return true;
    }

    public String getLastName() {
        return this.lastName;
    }

    public boolean setLastName(String newLastName) {
        this.firstName = newLastName;
        return true;
    }

    public String getPhone() {
        return this.phone;
    }

    public boolean setPhone(String newPhone) {
        if(!newPhone.matches("\\(?\\+[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})?")) return false;
        this.firstName = newPhone;
        return true;
    }

    public String getCPF() {
        return this.cpf;
    }

    public boolean setCPF(String newCPF) {
        if(!newCPF.matches("^[0-9]{11}$")) return false;
        if(!VerificationUtils.validateCPF(newCPF)) return false;
        this.cpf = newCPF;
        return true;
    }

    public boolean comparePasswords(String compPassword) {
        return this.password.equals(compPassword);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

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
