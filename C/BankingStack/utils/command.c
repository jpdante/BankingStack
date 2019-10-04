#include "command.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "menu.h"
#include "../manager/client_manager.h"
#include "../manager/account_manager.h"

void processCreateClient() {
    char *screen[1]={ "Create Client" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TClient client;
    client.id = getNextClientID();
    printf("ID: %i\n", client.id);
    if(!readString("Name: ", &client.name)) return;
    if(!readString("CPF: ", &client.cpf)) return;
    if(hasCPF(client.cpf)) {
        char *screen[1]={ "There is already a customer with this CPF!" };
        menuPrintWindowLeft(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
        return;
    }
    if(!readString("Phone: ", &client.phone)) return;
    addClient(client);
}

void processCreateAccount() {
    char *screen[1]={ "Create Account" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount account;
    account.id = getNextAccountID();
    printf("ID: %i\n", account.id);
    TClient client;
    if(!readClient("Client ID: ", &client)) return;
    account.client = client;
    account.balance = 0;
    account.active = 1;
    time_t now;
    time(&now);
    struct tm *local = localtime(&now);
    account.creationDate.day =local->tm_mday;
    account.creationDate.month =local->tm_mon + 1;
    account.creationDate.year =local->tm_year + 1900;
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
    int id;
    if(!readFloat("Account ID: ", &id)) return;
    char *msg = searchAccount(id);
    printf("%s", msg);
}

void processDeposit() {
    char *screen[1]={ "Deposit" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount *account;
    if(!readAccount("Account ID: ", account)) return;
    float amount;
    if(!readFloat("Amount: ", &amount)) return;
    if(accountDeposit(*account, amount)) {
        char *screen[1]={ "Deposit made successfully!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    } else {
        char *screen[1]={ "Failed to make deposit!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    }
}

void processWithdraw() {
    char *screen[1]={ "Withdraw" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount *account;
    if(!readAccount("Account ID: ", account)) return;
    float amount;
    if(!readFloat("Amount: ", &amount)) return;
    if(accountWithdraw(*account, amount)) {
        char *screen[1]={ "Withdrawal successfully!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    } else {
        char *screen[1]={ "Failed to perform withdrawal!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    }
}

void processTransfer() {
    char *screen[1]={ "Transfer" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount *from;
    if(!readAccount("From Account ID: ", from)) return;
    TAccount *to;
    if(!readAccount("From Account ID: ", to)) return;
    float amount;
    if(!readFloat("Amount: ", &amount)) return;
    if(accountTransfer(*from, *to, amount)) {
        char *screen[1]={ "Transfer completed successfully!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    } else {
        char *screen[1]={ "Failed to perform transfer!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    }
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
        TClient *client = getClient(id);
        if(response) {
            *response = *client;
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