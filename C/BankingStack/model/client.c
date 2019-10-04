#include "client.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "../utils/menu.h"
#include "../utils/convert.h"

void printClient(TClient client) {
    char *screen[4]={
            "ID: "
            "Nome: ",
            "CPF: ",
            "Phone: "
    };
    char *sID = intToString(client.id);
    strcat(screen[0], sID);
    free(sID);
    strcat(screen[1], client.name);
    strcat(screen[2], client.cpf);
    strcat(screen[3], client.phone);
    menuPrintWindowLeft("Client", screen, sizeof(screen)/sizeof(screen[0]), NULL);
}