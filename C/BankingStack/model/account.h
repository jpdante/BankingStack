#include "client.h"
#ifndef BANKINGSTACK_ACCOUNT_H
#define BANKINGSTACK_ACCOUNT_H

typedef struct Date {
    int day;
    int month;
    int year;
}TDate;

typedef struct account {
    int id;
    TClient client;
    TDate creationDate;
    float balance;
    int active;
}TAccount;

void deposit(TAccount account, float amount);
int debit(TAccount account, float amount);
int transfer(TAccount from, TAccount to, float amount);
void printAccount(TAccount account);

#endif //BANKINGSTACK_ACCOUNT_H
