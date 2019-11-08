package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.model.SpecialAccount;
import com.ellisiumx.bankingstack.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsCommand extends Command {
    public ListAccountsCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        List<String> content = new ArrayList<>();
        for(Client client : this.programContext.getClientManager().getClients()) {
            for(Account account : client.getAccounts()) {
                content.add("#l#" + String.format("%04d", account.getAccountID()));
                content.add("#l   ├─ Client ID: " + account.getClient().getClientID());
                content.add("#l   ├─ Balance: " + account.getBalance());
                content.add("#l   ├─ Creation Date: " + account.getCreationDate());
                if(account instanceof SpecialAccount) {
                    content.add("#l   ├─ Special Account: Yes");
                    content.add("#l   └─ Account Limit: " + ((SpecialAccount) account).getAccountLimit());
                } else {
                    content.add("#l   └─ Special Account: No");
                }
            }
        }
        MenuUtils.printWindow("Accounts", content.toArray(new String[0]));
        return 0;
    }
}
