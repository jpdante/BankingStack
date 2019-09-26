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

import java.util.ArrayList;
import java.util.List;

public class ListClientAccountsCommand extends Command {
    public ListClientAccountsCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        List<String> content = new ArrayList<>();
        Pair<Boolean, Client> client = CommandUtils.GetClient(this.programContext);
        if(!client.getKey()) return Error();

        for(Account account : client.getValue().getAccounts()) {
            content.add("#l#" + String.format("%04d", account.getAccountID()));
            if(account instanceof SpecialAccount) {
                content.add("#l   ├─ Special Account: Yes");
                content.add("#l   ├─ Account Limit: " + ((SpecialAccount) account).getAccountLimit());
            } else {
                content.add("#l   ├─ Special Account: No");
            }
            content.add("#l   ├─ Balance: " + account.getBalance());
            content.add("#l   ├─ Creation Date: " + account.getCreationDate());
            content.add("#l   └─ Client ID: " + account.getClient().getClientID());
        }
        MenuUtils.printWindow(client.getValue().getFirstName() + "'s Accounts", content.toArray(new String[0]));
        return 0;
    }

    private static int Error() {
        MenuUtils.printWindow("Error", new String[]{
                "#cFailed to get client!",
        });
        return 0;
    }
}
