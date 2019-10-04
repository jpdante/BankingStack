#include "main.h"
#include <stdio.h>
#include <stdlib.h>
#include "utils/menu.h"
#include "utils/command.h"

int isRunning = 1;

int main() {
    while(isRunning) {
        int response = mainScreen();
        if(response) {
            isRunning = 0;
        }
    }
    printf("Exiting...");
    return 0;
}

int mainScreen() {
    char *screen[9]={
            "           Welcome to BankingStack",
            "",
            "1. Create client        !  6. Deposit",
            "2. Create account       !  7. Withdraw",
            "3. List clients         !  8. Transfer",
            "4. List accounts        !",
            "5. Search account       !",
            "0. Exit",
            ""
    };
    menuPrintWindowLeft("BankingStack", screen, sizeof(screen)/sizeof(screen[0]), "Enter one of the options above");
    int option = 0;
    scanf("%i", &option);
    switch (option) {
        case 0:
            return 1;
        case 1:
            processCreateClient();
            break;
        case 2:
            processCreateAccount();
            break;
        case 3:
            processListClients();
            break;
        case 4:
            processListAccounts();
            break;
        case 5:
            processSearchAccount();
            break;
        case 6:
            processDeposit();
            break;
        case 7:
            processWithdraw();
            break;
        case 8:
            processTransfer();
            break;
        default:
            break;
    }
    return 0;
}