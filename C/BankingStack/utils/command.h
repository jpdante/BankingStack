#ifndef BANKINGSTACK_COMMAND_H
#define BANKINGSTACK_COMMAND_H

void processCreateClient();
void processCreateAccount();
void processListClients();
void processListAccounts();
void processSearchAccount();
void processDeposit();
void processWithdraw();
void processTransfer();
int readString(char info[], char *response);
int readFloat(char info[], float *response);
int tryAgain();

#endif //BANKINGSTACK_COMMAND_H
