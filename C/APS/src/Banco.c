#include <stdio.h>
#include <string.h>
#include "menu.h"

int main(void)
{
    char opcao;

    criarMenuSuperior();
    criarCabecalho("MENU");
    criarMenuLinhaHorizontal();
    criarItem(" ");
    criarItem("1- Cadastro de vlieentes");
    criarItem("2- Listar clientes");
    criarItem("3- Cadastro de contas / Contas especiais");
    criarItem("4- Realizar deposito");
    criarItem("5- Realizao debito (Saque)");
    criarItem("6- Listar contas cadastradas");
    criarItem("7- Transferencia");
    criarItem("8- Remover conta");
    criarItem(" ");
    criarItem("0- Sair");
    criarMenuRodape();
}