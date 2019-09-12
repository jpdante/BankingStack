package com.ellisiumx.bankingstack;

import com.ellisiumx.bankingstack.utils.MenuUtils;

public class Main {

    public static void main(String[] args) {
        MenuUtils.printWindow("BankingStack", new String[] {
                "Bem Vindo ao BankingStack",
                "",
                "1. Login",
                "2. Cadastro",
                "3. Listar Usuarios",
                "4. Listar Contas hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",
                "5. Deletar Usuario",
                "6. Deletar Conta",
                "7. Sair",
                "",
        }, "Digite uma das opcões acima");
    }
}
