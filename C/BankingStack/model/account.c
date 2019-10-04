#include "account.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "../utils/menu.h"
#include "../utils/convert.h"

void accountDeposit(TAccount account, float amount) {
    account.balance += amount;
}

int accountDebit(TAccount account, float amount) {
    if(account.balance - amount < -1000) return 0;
    account.balance -= amount;
    return 1;
}

int accountTransfer(TAccount from, TAccount to, float amount) {
    if(from.balance - amount < -1000) return 0;
    from.balance -= amount;
    to.balance += amount;
    return 1;
}

void accountPrint(TAccount account) {
    char *screen[4]={
            "ID: ",
            "Active: ",
            "Balance: ",
            "Creation Date: "
    };
    char *sID = intToString(account.id);
    strcat(screen[0], sID);
    free(sID);
    char *sActive = intToYesNo(account.active);
    strcat(screen[1], sActive);
    free(sActive);
    char *sBalance = floatToString(account.balance);
    strcat(screen[2], sBalance);
    free(sBalance);
    char *sDate = tDataToString(account.creationDate);
    strcat(screen[3], sDate);
    free(sDate);
    menuPrintWindowLeft("Account", screen, sizeof(screen)/sizeof(screen[0]), NULL);
}