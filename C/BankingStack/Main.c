#include <stdio.h>
#include "Utils/MenuUtils.h"

int main() {
    char *string_database[5]={'\0'};
    string_database[0]="Florida";
    string_database[1]="Oregon";
    string_database[2]="California";
    string_database[3]="Georgia";
    menuPrintWindow("Titulo", string_database, 4);
    /*menuCreateHeaderMenu();
    menuCreateHeader("MENU");
    menuCreateHorizontalLine();
    menuCreateItem(" ");
    menuCreateItem("1- Cadastro de clieentes");
    menuCreateItem("2- Listar clientes");
    menuCreateItem("3- Cadastro de contas / Contas especiais");
    menuCreateItem("4- Realizar deposito");
    menuCreateItem("5- Realizao debito (Saque)");
    menuCreateItem("6- Listar contas cadastradas");
    menuCreateItem("7- Transferencia");
    menuCreateItem("8- Remover conta");
    menuCreateItem(" ");
    menuCreateItem("0- Sair");
    menuCreateFoot();*/
    return 0;
}