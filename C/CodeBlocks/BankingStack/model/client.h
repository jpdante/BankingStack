#ifndef BANKINGSTACK_CLIENT_H
#define BANKINGSTACK_CLIENT_H

typedef struct client {
    int id;
    char name[10];
    char cpf[11];
    char phone[10];
}TClient;

void clientPrint(TClient client);

#endif //BANKINGSTACK_CLIENT_H
