#ifndef BANKINGSTACK_CLIENT_MANAGER_H
#define BANKINGSTACK_CLIENT_MANAGER_H

#include "../model/client.h"

void addClient(TClient client);
char* listClients();
int hasCPF();

#endif //BANKINGSTACK_CLIENT_MANAGER_H
