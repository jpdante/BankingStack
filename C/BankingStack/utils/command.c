#include <stdlib.h>
#include <stdio.h>
#include "menu.h"
#include "../model/client.h"
#include "../manager/client_manager.h"

void processCreateClient() {
    char *screen[1]={ "Create Client" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TClient client;
    client.id = getNextClientID();
    printf("ID: %i\n", client.id);
    if(!readString("Name: ", client.name)) return;
    if(!readString("CPF: ", client.cpf)) return;
    if(!readString("Phone: ", client.phone)) return;
    addClient(client);
}

void processCreateAccount() {

}

void processListClients() {
    char *screen[1]={ "List Clients" };
    menuPrintWindowLeft(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    char *clientsMsg = listClients();
    printf(clientsMsg);
    free(clientsMsg);
}

void processListAccounts() {

}

void processSearchAccount() {

}

void processDeposit() {

}

void processWithdraw() {

}

void processTransfer() {

}

int readString(char info[], char *response) {
    printf(info);
    if(scanf("%s", response)) {
        return 1;
    } else {
        if(tryAgain()) {
            return readString(info, response);
        }
        return 0;
    }
}

int readFloat(char info[], float *response) {
    printf(info);
    if(scanf("%f", response)) {
        return 1;
    } else {
        if(tryAgain()) {
            return readFloat(info, response);
        }
        return 0;
    }
}

int tryAgain() {
    printf("Try Again ? (y/n) ");
    char option;
    scanf(" %c", &option);
    switch(option) {
        case 'y':
        case 'Y':
            return 1;
        default:
            return 0;
    }
}