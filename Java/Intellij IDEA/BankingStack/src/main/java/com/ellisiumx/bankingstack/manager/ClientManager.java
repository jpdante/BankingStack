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

    public boolean addClients(Client[] clients) {
        boolean ok = true;
        for (Client client : clients) {
            if(!addClient(client)) ok = false;
        }
        return ok;
    }

    public boolean removeClient(Client client) {
        if(!clients.contains(client)) return false;
        clients.remove(client);
        return true;
    }

    public Client getClientByID(int id) {
        if(id < clients.size() && clients.get(id) != null && clients.get(id).getClientID() == id) return clients.get(id);
        for(Client client : clients) {
            if(client.getClientID() == id) return client;
        }
        return null;
    }

    public Client getClientByCPF(String cpf) {
        for(Client client : clients) {
            if(client.getCPF().equals(cpf)) return client;
        }
        return null;
    }

    public boolean hasClientWithCPF(String cpf) {
        for(Client client : clients) {
            if(client.getCPF().equals(cpf)) return true;
        }
        return false;
    }

    public boolean hasClientWithID(int id) {
        if(id < clients.size() && clients.get(id) != null && clients.get(id).getClientID() == id) return true;
        for(Client client : clients) {
            if(client.getClientID() == id) return true;
        }
        return false;
    }
}
