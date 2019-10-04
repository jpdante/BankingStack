#ifndef BANKINGSTACK_ACCOUNT_MANAGER_H
#define BANKINGSTACK_ACCOUNT_MANAGER_H

#include "../model/account.h"

void addAccount(TAccount account);
char* listAccounts();
char* searchAccount(int id);
TAccount getAccount(int id);
int removeAccount(int id);

#endif //BANKINGSTACK_ACCOUNT_MANAGER_H
