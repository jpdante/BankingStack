package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import javafx.util.Pair;

import java.io.IOException;

public class DeleteAccountCommand extends Command {
    public DeleteAccountCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        Pair<Boolean, Account> account = CommandUtils.GetAccount(this.programContext);
        if(!account.getKey()) return Error();
        if(account.getValue().getBalance() < 0.0d) {
            MenuUtils.printWindow("Delete Account", new String[] {
                    "#c&4You need to pay your credit before closing this account!&r"
            });
            return Error();
        }
        if(account.getValue().getBalance() != 0.0d) {
            MenuUtils.printWindow("Delete Account", new String[] {
                    "#c&4You need to withdraw all money from the account before it can be closed!&r"
            });
            return Error();
        }

        account.getValue().getClient().removeAccount(account.getValue());

        MenuUtils.printWindow("Delete Account", new String[] {
                "#c&2Account deleted successfully!&r"
        });

        try {
            this.programContext.getDatabaseManager().SaveClients(this.programContext.getClientManager().getClients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int Error() {
        MenuUtils.printWindow("Error", new String[]{
                "#c&4Failed to close account&r",
        });
        return 0;
    }
}