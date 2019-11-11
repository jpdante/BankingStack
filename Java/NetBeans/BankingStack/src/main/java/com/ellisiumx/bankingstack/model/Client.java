package com.ellisiumx.bankingstack.model;

import com.ellisiumx.bankingstack.utils.VerificationUtils;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int clientID;
    private String firstName;
    private String lastName;
    private String phone;
    private String cpf;
    private List<Account> accounts;

    public Client(int clientID, String firstName, String lastName, String phone, String cpf) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.cpf = cpf;
        this.accounts = new ArrayList<>();
    }

    public Client(int clientID) {
        this.clientID = clientID;
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.cpf = "";
        this.accounts = new ArrayList<>();
    }

    public int getClientID() {
        return this.clientID;
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

    public List<Account> getAccounts() { return this.accounts; }

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
