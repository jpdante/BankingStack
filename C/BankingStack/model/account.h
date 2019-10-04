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

int accountDeposit(TAccount account, float amount);
int accountWithdraw(TAccount account, float amount);
int accountTransfer(TAccount from, TAccount to, float amount);
void accountPrint(TAccount account);

#endif //BANKINGSTACK_ACCOUNT_H
