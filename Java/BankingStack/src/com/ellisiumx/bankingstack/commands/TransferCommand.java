package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import javafx.util.Pair;

import java.io.IOException;

public class TransferCommand extends Command {
    public TransferCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        Pair<Boolean, Account> from = CommandUtils.GetAccount(this.programContext);
        if(!from.getKey()) return Error();
        Pair<Boolean, Double> amount = CommandUtils.GetAmount(this.programContext.getConsoleScanner());
        if(!amount.getKey()) return Error();
        Pair<Boolean, Account> to = CommandUtils.GetAccount(this.programContext);
        if(!to.getKey()) return Error();
        if(from.getValue() == to.getValue()) {
            MenuUtils.printWindow("Error", new String[] {
                    "#c&4You cannot transfer to yourself.&r",
            });
            return Error();
        }

        if(from.getValue().removeFunds(amount.getValue()) && to.getValue().addFunds(amount.getValue())) {
            MenuUtils.printWindow("Transfer", new String[] {
                    "#c&2Successful transfer!&r"
            });
        } else {
            MenuUtils.printWindow("Transfer", new String[] {
                    "#c&4Failed to transfer!&r"
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
                "#c&4Failed to transfer&r",
        });
        return 0;
    }
}