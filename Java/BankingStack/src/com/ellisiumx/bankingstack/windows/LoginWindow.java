package com.ellisiumx.bankingstack.windows;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.utils.MenuUtils;

public class LoginWindow extends Window {
    public LoginWindow(Main programContext) {
        super(programContext);
    }

    public int Run() {
        MenuUtils.printWindow(new String[] { "&bLogin&r" });
        System.out.print("Account ID: ");
        int id = this.programContext.getConsoleScanner().nextInt();
        System.out.print("Password: ");
        String password = this.programContext.getConsoleScanner().nextLine();
        return 0;
    }
}
