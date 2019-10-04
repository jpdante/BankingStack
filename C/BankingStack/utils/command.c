#include "command.h"
#include <stdlib.h>
#include <stdio.h>
#include "menu.h"
#include "../manager/client_manager.h"
#include "../manager/account_manager.h"

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
    char *screen[1]={ "Create Account" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount account;
    account.id = getNextAccountID();
    printf("ID: %i\n", account.id);
    if(!readClient("Client ID: ", &account.client)) return;
    addAccount(account);
}

void processListClients() {
    char *screen[1]={ "List Clients" };
    menuPrintWindowLeft(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    char *clientsMsg = listClients();
    printf(clientsMsg);
    free(clientsMsg);
}

void processListAccounts() {
    char *screen[1]={ "List Accounts" };
    menuPrintWindowLeft(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    char *accountsMsg = listAccounts();
    printf(accountsMsg);
    free(accountsMsg);
}

void processSearchAccount() {

}

void processDeposit() {

}

void processWithdraw() {

}

void processTransfer() {

}

int readAccount(char info[], TAccount *response) {
    printf(info);
    int id;
    if(scanf("%i", &id)) {
        response = getAccount(id);
        if(response) {
            return 1;
        } else {
            char *screen[1]={ "Account not found!" };
            menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
            if(tryAgain()) {
                return readAccount(info, response);
            }
            return 0;
        }
    } else {
        if(tryAgain()) {
            return readAccount(info, response);
        }
        return 0;
    }
}

int readClient(char info[], TClient *response) {
    printf(info);
    int id;
    if(scanf("%i", &id)) {
        response = getClient(id);
        if(response) {
            return 1;
        } else {
            char *screen[1]={ "Client not found!" };
            menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
            if(tryAgain()) {
                return readClient(info, response);
            }
            return 0;
        }
    } else {
        if(tryAgain()) {
            return readClient(info, response);
        }
        return 0;
    }
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