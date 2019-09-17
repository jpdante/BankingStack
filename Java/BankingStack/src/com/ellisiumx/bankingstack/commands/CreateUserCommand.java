package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.EncryptionUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;

import java.io.IOException;

public class CreateUserCommand extends Command {

    private Client user;

    public CreateUserCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        MenuUtils.printWindow(new String[]{"&1Create User&r"});
        user = new Client(GetID());
        ReadFirstName();
        ReadLastName();
        ReadPhone();
        ReadCPF();
        ReadPassword();
        MenuUtils.printWindow("User", new String[]{
                "#l" + user.getClientID(),
                "#l" + user.getFirstName(),
                "#l" + user.getLastName(),
                "#l" + user.getPhone(),
                "#l" + user.getCPF(),
                "#l" + user.getPassword()
        });
        this.programContext.getUsers().add(user);
        try {
            this.programContext.getDatabaseManager().SaveUsers(this.programContext.getUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int GetID() {
        int ID = this.programContext.getUsers().size() + 1;
        while(true) {
            boolean containsID = false;
            for(Client user : this.programContext.getUsers()) {
                if(user.getClientID() == ID) {
                    ID++;
                    containsID = true;
                }
            }
            if(!containsID) return ID;
        }
    }

    private void ReadFirstName() {
        while(true) {
            System.out.print("First Name: ");
            String data = this.programContext.getConsoleScanner().nextLine();
            if(data.length() <= 0 || !user.setFirstName(data)) {
                MenuUtils.printWindow("Error", new String[] {
                        "#c&4Invalid name, please try again&r",
                });
                continue;
            }
            return;
        }
    }

    private void ReadLastName() {
        while(true) {
            System.out.print("Last Name: ");
            String data = this.programContext.getConsoleScanner().nextLine();
            if(data.length() <= 0 || !user.setLastName(data)) {
                MenuUtils.printWindow("Error", new String[] {
                        "#c&4Invalid name, please try again&r",
                });
                continue;
            }
            return;
        }
    }

    private void ReadPhone() {
        while(true) {
            System.out.print("Phone: ");
            String data = this.programContext.getConsoleScanner().nextLine();
            if(data.length() <= 0 || !user.setPhone(data)) {
                MenuUtils.printWindow("Error", new String[] {
                        "#c&4Invalid phone, please try again&r",
                        "",
                        "Valid syntax:",
                        "&b+xx yy zzzzz-zzzz&r",
                });
                continue;
            }
            return;
        }
    }

    private void ReadCPF() {
        while(true) {
            System.out.print("CPF: ");
            String data = this.programContext.getConsoleScanner().nextLine();
            if(data.length() <= 0 || !user.setCPF(data)) {
                MenuUtils.printWindow("Error", new String[] {
                        "#c&4Invalid CPF, please try again&r",
                        "",
                        "Valid syntax:",
                        "&b12345678910&r",
                });
                continue;
            }
            return;
        }
    }

    private void ReadPassword() {
        while(true) {
            System.out.print("Password: ");
            String data = this.programContext.getConsoleScanner().nextLine();
            if(data.length() <= 8) {
                MenuUtils.printWindow("Error", new String[] {
                        "#c&4Invalid password, please try again&r",
                        "",
                        "&ePassword must be at least 8 characters&r",
                });
                continue;
            }
            String encryptedPassword = EncryptionUtils.encodeSHA256(data);
            this.user.setPassword(encryptedPassword);
            return;
        }
    }
}
