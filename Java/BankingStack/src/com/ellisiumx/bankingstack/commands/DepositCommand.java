package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import javafx.util.Pair;

import java.io.IOException;

public class DepositCommand extends Command {
    public DepositCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        Pair<Boolean, Account> account = CommandUtils.GetAccount(this.programContext);
        if(!account.getKey()) return Error();
        Pair<Boolean, Double> amount = CommandUtils.GetAmount(this.programContext.getConsoleScanner());
        if(!amount.getKey()) return Error();

        if(account.getValue().addFunds(amount.getValue())) {
            MenuUtils.printWindow("Deposit", new String[] {
                    "#c&2$" + amount + " successfully deposited!&r"
            });
        } else {
            MenuUtils.printWindow("Deposit", new String[] {
                    "#c&4Failed to deposit!&r"
            });
        }

        try {
            this.programContext.getDatabaseManager().SaveClients(this.programContext.getClientManager().getClients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int Error() {
        MenuUtils.printWindow("Error", new String[]{
                "#c&4Failed to deposit&r",
        });
        return 0;
    }
}