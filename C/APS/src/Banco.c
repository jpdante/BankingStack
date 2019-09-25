#include <stdio.h>
#include <string.h>

//#include "menu.h"
//#include "cliente.h"
#include "gerenciaClientes.h"

int main(void)
{ 
    int opcao;

    criarMenuSuperior();
    criarCabecalho("MENU");
    criarMenuLinhaHorizontal();
    criarItem(" ");
    criarItem("1- Cadastro de clieentes");
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
    printf("\nDigite uma das opcoes: \n");

    printf(">");
    scanf(" %i", &opcao);

        switch (opcao);
        {
            case 1: printf("%i" oi(1,1));
                break;
            /*case 2: listarClientes();
                break;
            case 3: cadastrarConta();
                break;
            case 4: realizarDeposito();
                break;
            case 5: realizarDebito();
                break; 
            case 6: listarContas();
                break;
            case 7: Tranferencia();
                break;
            case 8: removerConta();
                break;*/
            default: printf("\nErro, opcao invalida! \nDigite uma opcao valida.\n");
                break;
    }
}