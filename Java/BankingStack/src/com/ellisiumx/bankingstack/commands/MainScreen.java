package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.utils.MenuUtils;

public class MainScreen extends Command {
    public MainScreen(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        MenuUtils.printWindow("BankingStack", new String[] {
                "Welcome to BankingStack",
                "",
                "#l1. Create client        │  7. Deposit",
                "#l2. Create account       │  8. Withdraw",
                "#l3. List clients         │  9. Transfer",
                "#l4. List accounts        │ 10. Delete account",
                "#l5. List client accounts │ 11. Delete client",
                "#l6. Search client        │",
                "#l0. Exit",
                "",
        }, "Enter one of the options above");
        int option = this.programContext.getConsoleScanner().nextInt();
        this.programContext.getConsoleScanner().nextLine();
        switch (option) {
            case 1:
                new CreateClientCommand(this.programContext).Run();
                break;
            case 2:
                new CreateAccountCommand(this.programContext).Run();
                break;
            case 3:
                new ListClientsCommand(this.programContext).Run();
                break;
            case 4:
                new ListAccountsCommand(this.programContext).Run();
                break;
            case 5:
                new ListClientAccountsCommand(this.programContext).Run();
                break;
            case 6:
                new SearchClientCommand(this.programContext).Run();
                break;
            case 7:
                new DepositCommand(this.programContext).Run();
                break;
            case 8:
                new WithdrawCommand(this.programContext).Run();
                break;
            case 9:
                new TransferCommand(this.programContext).Run();
                break;
            case 10:
                new DeleteAccountCommand(this.programContext).Run();
                break;
            case 11:
                new DeleteClientCommand(this.programContext).Run();
                break;
            case 0:
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