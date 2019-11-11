package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import javafx.util.Pair;

import java.io.IOException;

public class DeleteClientCommand extends Command {
    public DeleteClientCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        Pair<Boolean, Client> client = CommandUtils.GetClient(this.programContext);
        if(!client.getKey()) return Error();

        if(client.getValue().getAccounts().size() != 0) {
            MenuUtils.printWindow("Delete Client", new String[] {
                    "#cYou must close your accounts before you can delete this user!"
            });
            return Error();
        }

        if(!programContext.getClientManager().removeClient(client.getValue())) return Error();

        MenuUtils.printWindow("Delete Client", new String[] {
                "#cClient deleted successfully!"
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
                "#cFailed to delete client",
        });
        return 0;
    }
}