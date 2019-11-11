package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.ConversionUtils;
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
                    "#c$" + ConversionUtils.doubleToString(amount.getValue()) + " successfully deposited!"
            });
        } else {
            MenuUtils.printWindow("Deposit", new String[] {
                    "#cFailed to deposit!"
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
                "#cFailed to deposit",
        });
        return 0;
    }
}