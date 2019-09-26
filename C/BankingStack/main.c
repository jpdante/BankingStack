#include "main.h"
#include <stdio.h>
#include "utils/menu.h"

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
    char *screen[10]={
            "           Welcome to BankingStack",
            "",
            "1. Create client        !  7. Deposit",
            "2. Create account       !  8. Withdraw",
            "3. List clients         !  9. Transfer",
            "4. List accounts        ! 10. Delete account",
            "5. List client accounts ! 11. Delete client",
            "6. Search client        !",
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
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        case 6:
            break;
        case 7:
            break;
        case 8:
            break;
        case 9:
            break;
        case 10:
            break;
        case 11:
            break;
        default:
            break;
    }
    return 0;
}