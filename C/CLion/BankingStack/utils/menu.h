#ifndef BANKINGSTACK_MENU_H
#define BANKINGSTACK_MENU_H

void menuPrintWindowLeft(char *header, char **content, int size, char *footer);
void menuPrintWindowCenter(char *header, char **content, int size, char *footer);
void menuPrintWindowRight(char *header, char **content, int size, char *footer);
int menuGetMaxSize(char *header, char **content, int size, char *footer);
void menuPrintHeader(char *header, int windowSize);
void menuPrintItemCenter(char *content, int windowSize);
void menuPrintItemRight(char *content, int windowSize);
void menuPrintItemLeft(char *content, int windowSize);
void menuPrintFooter(char *footer, int windowSize);
#endif //BANKINGSTACK_MENU_H
