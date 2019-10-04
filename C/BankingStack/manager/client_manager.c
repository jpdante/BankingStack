#include "client_manager.h"
#include "../utils/convert.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

TClient *clients;
int clientsSize = 0;
/*
 * 'ID: '    + int(String) + LineBreak = sizeof(char) * 4 + sizeof(char) * 12 + sizeof(char) * 1
 * 'Name: '  + String + LineBreak = sizeof(char) * 6 + sizeof(char) * 10 + sizeof(char) * 1
 * 'CPF: '   + String + LineBreak = sizeof(char) * 5 + sizeof(char) * 11 + sizeof(char) * 1
 * 'Phone: ' + String + LineBreak = sizeof(char) * 7 + sizeof(char) * 10 + sizeof(char) * 1
 * '--------------------' + LineBreak + \0 = sizeof(char) * 20 + sizeof(char) * 1
 */
const int clientStringSize =
        sizeof(char) * 4 + sizeof(char) * 12 + sizeof(char) * 1 +
        sizeof(char) * 6 + sizeof(char) * 10 + sizeof(char) * 1 +
        sizeof(char) * 5 + sizeof(char) * 11 + sizeof(char) * 1 +
        sizeof(char) * 7 + sizeof(char) * 10 + sizeof(char) * 1 +
        sizeof(char) * 20 + sizeof(char) * 1 + 1;

void addClient(TClient client) {
    if(clients) {
        clientsSize++;
        clients = realloc(clients, sizeof(TClient) * clientsSize);
    } else {
        clientsSize = 1;
        clients = malloc(sizeof(TClient) * clientsSize);
    }
    clients[clientsSize - 1] = client;
}

TClient* getClient(int id) {
    for(int i = 0; i < clientsSize; i++) {
        if(clients[i].id == id) {
            return &clients[i];
        }
    }
    return NULL;
}

char* listClients() {
    char *message = malloc(clientStringSize *clientsSize);
    strcpy(message, "");
    for(int i = 0; i < clientsSize; i++) {
        TClient client = clients[i];
        strcat(message, "ID: ");
        char *id = intToString(client.id);
        strcat(message, id);
        free(id);
        strcat(message, "\n");
        strcat(message, "Name: ");
        strcat(message, client.name);
        strcat(message, "\n");
        strcat(message, "CPF: ");
        strcat(message, client.cpf);
        strcat(message, "\n");
        strcat(message, "Phone: ");
        strcat(message, client.phone);
        strcat(message, "\n");
        strcat(message, "--------------------");
        strcat(message, "\n\0");
    }
    return message;
}

int hasCPF(char cpf[]) {
    for(int i = 0; i < clientsSize; i++) {
        if(clients[i].cpf == cpf) {
            return 1;
        }
    }
    return 0;
}

int getNextClientID() {
    return clientsSize + 1;
}