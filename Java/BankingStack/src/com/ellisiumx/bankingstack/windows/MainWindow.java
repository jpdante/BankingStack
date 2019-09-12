package com.ellisiumx.bankingstack.windows;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.utils.MenuUtils;

public class MainWindow extends Window {
    public MainWindow(Main programContext) {
        super(programContext);
    }

    public int Run() {
        MenuUtils.printWindow("BankingStack", new String[] {
                "Welcome to BankingStack",
                "",
                "#l1. Login",
                "#l2. Register",
                "#l3. Admin",
                "#l4. Sair",
                "",
        }, "Enter one of the options above");
        int option = programContext.getConsoleScanner().nextInt();
        switch (option) {
            case 1:
                new LoginWindow(this.programContext).Run();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                this.programContext.Exit();
                break;
            default:
                MenuUtils.printWindow("Error", new String[] {
                        "#c&4The option entered is invalid&r",
                });
                break;
        }
        return 0;
    }
}
