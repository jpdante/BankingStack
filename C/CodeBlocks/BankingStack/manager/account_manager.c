#include "account_manager.h"
#include <stdio.h>
#include "../utils/convert.h"
#include <stdlib.h>
#include <string.h>

TAccount *accounts;
int accountsSize = 0;
/*
 * 'ID: '        + int(String) + LineBreak = sizeof(char) * 4 + sizeof(char) * 12 + sizeof(char) * 1
 * 'Client ID: ' + int(String) + LineBreak = sizeof(char) * 11 + sizeof(char) * 12 + sizeof(char) * 1
 * 'Active: '    + int(String) + LineBreak = sizeof(char) * 8 + sizeof(char) * 12 + sizeof(char) * 1
 * 'Balance: '   + float(String) + LineBreak = sizeof(char) * 9 + sizeof(char) * 25 + sizeof(char) * 1
 * 'Creation Date: '  + String + LineBreak = sizeof(char) * 15 + sizeof(char) * 10 + sizeof(char) * 1
 * '--------------------' + LineBreak + \0 = sizeof(char) * 20 + sizeof(char) * 1
 */
const int accountStringSize =
        sizeof(char) * 4 + sizeof(char) * 12 + sizeof(char) * 1 +
        sizeof(char) * 11 + sizeof(char) * 12 + sizeof(char) * 1 +
        sizeof(char) * 8 + sizeof(char) * 12 + sizeof(char) * 1 +
        sizeof(char) * 9 + sizeof(char) * 25 + sizeof(char) * 1 +
        sizeof(char) * 15 + sizeof(char) * 10 + sizeof(char) * 1 +
        sizeof(char) * 20 + sizeof(char) * 1;

void addAccount(TAccount account) {
    if(accounts) {
        accountsSize++;
        accounts = realloc(accounts, sizeof(TAccount) * accountsSize);
    } else {
        accountsSize = 1;
        accounts = malloc(sizeof(TAccount) * accountsSize);
    }
    accounts[accountsSize - 1] = account;
}

char* listAccounts() {
    char *message = malloc(accountStringSize * accountsSize);
    strcpy(message, "");
    for(int i = 0; i < accountsSize; i++) {
        TAccount account = accounts[i];
        strcat(message, "ID: ");
        char *id = intToString(account.id);
        strcat(message, id);
        free(id);
        strcat(message, "\n");
        strcat(message, "Client ID: ");
        char *clientID = intToString(account.client.id);
        strcat(message, clientID);
        free(clientID);
        strcat(message, "\n");
        strcat(message, "Active: ");
        char *active = intToString(account.active);
        strcat(message, active);
        free(active);
        strcat(message, "\n");
        strcat(message, "Balance: ");
        char *balance = floatToString(account.balance);
        strcat(message, balance);
        free(balance);
        strcat(message, "\n");
        strcat(message, "Creation Date: ");
        char *tData = tDataToString(account.creationDate);
        strcat(message, tData);
        strcat(message, "\n");
        strcat(message, "--------------------");
        strcat(message, "\n\0");
    }
    return message;
}

char* searchAccount(int id) {
    char *message = malloc(accountStringSize);
    strcpy(message, "");
    TAccount *accountPtr = getAccount(id);
    if(accountPtr) {
        TAccount account = *accountPtr;
        strcat(message, "ID: ");
        char *id = intToString(account.id);
        strcat(message, id);
        free(id);
        strcat(message, "\n");
        strcat(message, "Client ID: ");
        char *clientID = intToString(account.client.id);
        strcat(message, clientID);
        free(clientID);
        strcat(message, "\n");
        strcat(message, "Active: ");
        char *active = intToString(account.active);
        strcat(message, active);
        free(active);
        strcat(message, "\n");
        strcat(message, "Balance: ");
        char *balance = floatToString(account.balance);
        strcat(message, balance);
        free(balance);
        strcat(message, "\n");
        strcat(message, "Creation Date: ");
        char *tData = tDataToString(account.creationDate);
        strcat(message, tData);
        free(tData);
        strcat(message, "\n");
        strcat(message, "--------------------");
        strcat(message, "\n\0");
    }
    return message;
}

TAccount* getAccount(int id) {
    for(int i = 0; i < accountsSize; i++) {
        if(accounts[i].id == id) {
            return &accounts[i];
        }
    }
    return NULL;
}

int removeAccount(int id) {
    return 1;
}

int getNextAccountID() {
    return accountsSize + 1;
}