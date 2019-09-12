package com.ellisiumx.bankingstack.model;

import com.ellisiumx.bankingstack.utils.VerificationUtils;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String phone;
    private String cpf;
    private String password;
    private List<Account> accounts;

    public User(int userID, String firstName, String lastName, String phone, String cpf, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.cpf = cpf;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public User(int userID) {
        this.userID = userID;
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.cpf = "";
        this.password = "";
        this.accounts = new ArrayList<>();
    }

    public int getUserID() {
        return this.userID;
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
        this.lastName = newLastName;
        return true;
    }

    public String getPhone() {
        return this.phone;
    }

    public boolean setPhone(String newPhone) {
        if(!newPhone.matches("\\(?\\+[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})?")) return false;
        this.phone = newPhone;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public Account[] getAccounts() { return (Account[]) this.accounts.toArray(); }

    public boolean addAccount(Account account) {
        if(this.accounts.contains(account)) return false;
        this.accounts.add(account);
        return true;
    }

    public boolean removeAccount(Account account) {
        if(!this.accounts.contains(account)) return false;
        this.accounts.remove(account);
        return true;
    }
}
