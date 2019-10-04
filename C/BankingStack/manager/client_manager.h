#ifndef BANKINGSTACK_CLIENT_MANAGER_H
#define BANKINGSTACK_CLIENT_MANAGER_H

#include "../model/client.h"

void addClient(TClient client);
TClient* getClient(int id);
char* listClients();
int hasCPF();
int getNextClientID();

#endif //BANKINGSTACK_CLIENT_MANAGER_H
