package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import javafx.util.Pair;

import java.io.IOException;

public class CreateClientCommand extends Command {
    public CreateClientCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        MenuUtils.printWindow(new String[]{"&1Create Client&r"});
        Pair<Boolean, String> firstName = CommandUtils.GetFirstName(this.programContext.getConsoleScanner());
        if (!firstName.getKey()) return Error();
        Pair<Boolean, String> lastName = CommandUtils.GetLastName(this.programContext.getConsoleScanner());
        if (!lastName.getKey()) return Error();
        Pair<Boolean, String> phone = CommandUtils.GetPhone(this.programContext.getConsoleScanner());
        if (!phone.getKey()) return Error();
        Pair<Boolean, String> cpf = CommandUtils.GetCPF(this.programContext.getConsoleScanner());
        if (!cpf.getKey()) return Error();
        if(this.programContext.getClientManager().hasClientWithCPF(cpf.getValue())) {
            MenuUtils.printWindow("CPF", new String[]{
                    "#c&4An account with this CPF already exists!&r",
            });
            return Error();
        }

        Client client = new Client(GetID(), firstName.getValue(), lastName.getValue(), phone.getValue(), cpf.getValue());
        if(!this.programContext.getClientManager().addClient(client)) return Error();
        MenuUtils.printWindow("Client", new String[]{
                "#l ID: " + client.getClientID(),
                "#l First Name: " + client.getFirstName(),
                "#l Last Name: " + client.getLastName(),
                "#l Phone: " + client.getPhone(),
                "#l CPF: " + client.getCPF()
        });
        try {
            this.programContext.getDatabaseManager().SaveClients(this.programContext.getClientManager().getClients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int Error() {
        MenuUtils.printWindow("Error", new String[]{
                "#c&4Failed to create client&r",
        });
        return 0;
    }

    private int GetID() {
        int ID = this.programContext.getClientManager().getClients().size();
        while (true) {
            boolean containsID = false;
            for (Client user : this.programContext.getClientManager().getClients()) {
                if (user.getClientID() == ID) {
                    ID++;
                    containsID = true;
                }
            }
            if (!containsID) return ID;
        }
    }
}
