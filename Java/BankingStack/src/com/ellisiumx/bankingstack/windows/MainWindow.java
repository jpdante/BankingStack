package com.ellisiumx.bankingstack.windows;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.utils.MenuUtils;

public class MainWindow extends Window {
    public MainWindow(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        MenuUtils.printWindow("BankingStack", new String[] {
                "Welcome to BankingStack",
                "",
                "#l1. Login",
                "#l2. Create user",
                "#l3. Create account",
                "#l4. List users",
                "#l5. List accounts",
                "#l6. List user accounts",
                "#l7. Delete user",
                "#l8. Close account",
                "#l9. Exit",
                "",
        }, "Enter one of the options above");
        int option = programContext.getConsoleScanner().nextInt();
        switch (option) {
            case 1:
                new LoginWindow(this.programContext).Run();
                break;
            case 2:
                new CreateUserWindow(this.programContext).Run();
                break;
            case 3:
                break;
            case 9:
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
