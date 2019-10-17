#include "command.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include "menu.h"
#include "../manager/client_manager.h"
#include "../manager/account_manager.h"
#include "../utils/convert.h"

void processCreateClient() {
    char *screen[1]={ "Create Client" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TClient client;
    client.id = getNextClientID();
    printf("ID: %i\n", client.id);
    if(!readString("Name: ", &client.name, 10)) return;
    if(!readString("CPF: ", &client.cpf, 11)) return;
    if(hasCPF(client.cpf)) {
        char *screen2[1]={ "There is already a customer with this CPF!" };
        menuPrintWindowLeft(NULL, screen2, sizeof(screen2)/sizeof(screen2[0]), NULL);
        return;
    }
    if(!readString("Phone: ", &client.phone, 10)) return;
    addClient(client);
}

void processCreateAccount() {
    char *screen[1]={ "Create Account" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount account;
    account.id = getNextAccountID();
    printf("ID: %i\n", account.id);
    TClient* client = readClient("Client ID: ");
    if(!client) return;
    account.client = *client;
    account.balance = 0.0f;
    account.active = 1;
    time_t now;
    time(&now);
    struct tm *local = localtime(&now);
    account.creationDate.day = local->tm_mday;
    account.creationDate.month = local->tm_mon + 1;
    account.creationDate.year = local->tm_year + 1900;
    addAccount(account);
}

void processListClients() {
    char *screen[1]={ "List Clients" };
    menuPrintWindowLeft(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    char *clientsMsg = listClients();
    printf("%s", clientsMsg);
    free(clientsMsg);
}

void processListAccounts() {
    char *screen[1]={ "List Accounts" };
    menuPrintWindowLeft(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    char *accountsMsg = listAccounts();
    printf("%s", accountsMsg);
    free(accountsMsg);
}

void processSearchAccount() {
    int id;
    if(!readInt("Account ID: ", &id)) return;
    char *msg = searchAccount(id);
    printf("%s", msg);
}

void processDeposit() {
    char *screen[1]={ "Deposit" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount *account= readAccount("Account ID: ");
    if(!account) return;
    float amount;
    if(!readFloat("Amount: ", &amount)) return;
    if(accountDeposit(account, amount)) {
        char *screen2[1]={ "Deposit made successfully!" };
        menuPrintWindowCenter(NULL, screen2, sizeof(screen2)/sizeof(screen2[0]), NULL);
    } else {
        char *screen3[1]={ "Failed to make deposit!" };
        menuPrintWindowCenter(NULL, screen3, sizeof(screen3)/sizeof(screen3[0]), NULL);
    }
}

void processWithdraw() {
    char *screen[1]={ "Withdraw" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount *account = readAccount("Account ID: ");
    if(!account) return;
    float amount;
    if(!readFloat("Amount: ", &amount)) return;
    if(accountWithdraw(account, amount)) {
        char *screen2[1]={ "Withdrawal successfully!" };
        menuPrintWindowCenter(NULL, screen2, sizeof(screen2)/sizeof(screen2[0]), NULL);
    } else {
        char *screen3[1]={ "Failed to perform withdrawal!" };
        menuPrintWindowCenter(NULL, screen3, sizeof(screen3)/sizeof(screen3[0]), NULL);
    }
}

void processTransfer() {
    char *screen[1]={ "Transfer" };
    menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
    TAccount *from = readAccount("From Account ID: ");
    if(!from) return;
    TAccount *to = readAccount("From Account ID: ");
    if(!to) return;
    float amount;
    if(!readFloat("Amount: ", &amount)) return;
    if(accountTransfer(from, to, amount)) {
        char *screen2[1]={ "Transfer completed successfully!" };
        menuPrintWindowCenter(NULL, screen2, sizeof(screen2)/sizeof(screen2[0]), NULL);
    } else {
        char *screen3[1]={ "Failed to perform transfer!" };
        menuPrintWindowCenter(NULL, screen3, sizeof(screen3)/sizeof(screen3[0]), NULL);
    }
}

TAccount* readAccount(char info[]) {
    int id;
    if(!readInt(info, &id)) return NULL;
    TAccount *account = getAccount(id);
    if(account) {
        return account;
    } else {
        char *screen[1]={ "Account not found!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
        if(tryAgain()) {
            return readAccount(info);
        }
    }
    return NULL;
}

TClient* readClient(char info[]) {
    int id;
    if(!readInt(info, &id)) return NULL;
    TClient *client = getClient(id);
    if(client) {
        return client;
    } else {
        char *screen[1]={ "Client not found!" };
        menuPrintWindowCenter(NULL, screen, sizeof(screen)/sizeof(screen[0]), NULL);
        if(tryAgain()) {
            return readClient(info);
        }
    }
    return NULL;
}

int readString(char info[], char *response, int count) {
    printf("%s", info);
    char input[10] = "%";
    char *strInt = intToString(count-1);
    strcat(input, strInt);
    strcat(input, "s");
    free(strInt);
    if(scanf(input, response)) {
        flush_in();
        return 1;
    } else {
        if(tryAgain()) {
            return readString(info, response, count);
        }
        return 0;
    }
}

int readFloat(char info[], float *response) {
    printf("%s", info);
    float value;
    if(scanf("%f", &value)) {
        *response = value;
        return 1;
    } else {
        if(tryAgain()) {
            return readFloat(info, response);
        }
        return 0;
    }
}

int readInt(char info[], int *response) {
    printf("%s", info);
    int value;
    if(scanf("%d", &value)) {
        *response = value;
        return 1;
    } else {
        if(tryAgain()) {
            return readInt(info, response);
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

void flush_in() {
    int ch;
    do {
        ch = fgetc(stdin);
    } while (ch != EOF && ch != '\n');
}