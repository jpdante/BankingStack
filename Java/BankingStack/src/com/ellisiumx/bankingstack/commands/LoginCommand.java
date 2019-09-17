package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.utils.MenuUtils;

public class LoginCommand extends Command {
    public LoginCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        MenuUtils.printWindow(new String[] { "&bLogin&r" });
        System.out.print("Account ID: ");
        int id = this.programContext.getConsoleScanner().nextInt();
        System.out.print("Password: ");
        String password = this.programContext.getConsoleScanner().nextLine();
        return 0;
    }
}
