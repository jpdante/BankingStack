#ifndef BANKINGSTACK_MENUUTILS_H
#define BANKINGSTACK_MENUUTILS_H

void menuReset();
void menuPrintWindow(char *title, char **content, int size);
int menuGetMaxSize(char **content, int size);
void menuPrintHeader(char *title, int windowSize);
void menuCreateHorizontalLine();
void menuCreateItem(char str[]);
void menuCreateFoot();

#endif //BANKINGSTACK_MENUUTILS_H
