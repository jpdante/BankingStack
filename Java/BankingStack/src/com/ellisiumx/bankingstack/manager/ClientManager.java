package com.ellisiumx.bankingstack.manager;

import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.ConversionUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientManager {
    private List<Client> clients;

    public ClientManager() {
        this.clients = new ArrayList<>();
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(this.clients);
    }

    public boolean addClient(Client client) {
        if(clients.contains(client)) return false;

        clients.add(client);
        return true;
    }

    public String printClientsList() {
        List<String> content = new ArrayList<>();
        for(Client user : new Client[] {}) {
            content.add("#l#" + String.format("%04d", user.getClientID()));
            content.add("#l   ├─ " + user.getFirstName() + " " + user.getLastName());
            content.add("#l   ├─ " + user.getPhone());
            content.add("#l   ├─ " + ConversionUtils.cpfToString(user.getCPF()));
            content.add("#l   └─ " + user.getPassword());
        }
        return MenuUtils.getWindow("Clients", content.toArray(new String[0]));
    }
}
