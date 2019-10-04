#ifndef BANKINGSTACK_COMMAND_H
#define BANKINGSTACK_COMMAND_H

#include "../model/client.h"
#include "../model/account.h"

void processCreateClient();
void processCreateAccount();
void processListClients();
void processListAccounts();
void processSearchAccount();
void processDeposit();
void processWithdraw();
void processTransfer();
int readAccount(char info[], TAccount *response);
int readClient(char info[], TClient *response);
int readString(char info[], char *response);
int readFloat(char info[], float *response);
int tryAgain();

#endif //BANKINGSTACK_COMMAND_H
