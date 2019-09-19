package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.model.SpecialAccount;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.ConversionUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import com.ellisiumx.bankingstack.utils.VerificationUtils;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Date;

public class CreateAccountCommand extends Command {

    private Account account;

    public CreateAccountCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        MenuUtils.printWindow(new String[]{"&1Create Account&r"});
        Pair<Boolean, Client> client = CommandUtils.GetClient(this.programContext);
        if(!client.getKey()) return Error();
        Pair<Boolean, Double> limit = CommandUtils.GetLimit(this.programContext.getConsoleScanner());
        if(!limit.getKey()) return Error();

        if(CommandUtils.GetSpecialAccount(this.programContext.getConsoleScanner())) account = new SpecialAccount(GetID(), client.getValue(), new Date(), limit.getValue());
        else account = new Account(GetID(), client.getValue(), new Date());
        if(!account.getClient().addAccount(account)) return Error();

        if(account instanceof SpecialAccount) {
            MenuUtils.printWindow("Account", new String[]{
                    "#l ID: " + account.getAccountID(),
                    "#l Client ID: " + account.getClient().getClientID(),
                    "#l IsSpecialAccount: Yes",
                    "#l Limit: " + ((SpecialAccount)account).getAccountLimit(),
                    "#l Balance: " + account.getBalance(),
                    "#l Creation Date: " + account.getCreationDate(),
            });
        } else {
            MenuUtils.printWindow("Account", new String[]{
                    "#l ID: " + account.getAccountID(),
                    "#l Client ID: " + account.getClient().getClientID(),
                    "#l IsSpecialAccount: No",
                    "#l Balance: " + account.getBalance(),
                    "#l Creation Date: " + account.getCreationDate(),
            });
        }
        try {
            this.programContext.getDatabaseManager().SaveClients(this.programContext.getClientManager().getClients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int Error() {
        MenuUtils.printWindow("Error", new String[]{
                "#c&4Failed to create account&r",
        });
        return 0;
    }

    private int GetID() {
        int ID = 0;
        while(true) {
            boolean containsID = false;
            for(Client client : this.programContext.getClientManager().getClients()) {
                for(Account account : client.getAccounts()) {
                    if(account.getAccountID() == ID) {
                        ID++;
                        containsID = true;
                    }
                }
            }
            if(!containsID) return ID;
        }
    }
}
