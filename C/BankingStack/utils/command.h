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
TAccount* readAccount(char info[]);
TClient* readClient(char info[]);
int readString(char info[], char *response, int count);
int readFloat(char info[], float *response);
int readInt(char info[], int *response);
int tryAgain();
void flush_in();

#endif //BANKINGSTACK_COMMAND_H
