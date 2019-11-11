package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.ConversionUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;

public class ListClientsCommand extends Command {
    public ListClientsCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        List<String> content = new ArrayList<>();
        for(Client client : this.programContext.getClientManager().getClients()) {
            content.add("#l#" + String.format("%04d", client.getClientID()));
            content.add("#l   ├─ Name: " + client.getFirstName() + " " + client.getLastName());
            content.add("#l   ├─ Phone: " + client.getPhone());
            content.add("#l   └─ CPF: " + ConversionUtils.cpfToString(client.getCPF()));
        }
        MenuUtils.printWindow("Clients", content.toArray(new String[0]));
        return 0;
    }
}
