package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.CommandUtils;
import com.ellisiumx.bankingstack.utils.ConversionUtils;
import com.ellisiumx.bankingstack.utils.MenuUtils;
import javafx.util.Pair;

public class SearchClientCommand extends Command {
    public SearchClientCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        Pair<Boolean, Client> client = CommandUtils.GetClient(this.programContext);
        if(!client.getKey()) return Error();
        MenuUtils.printWindow("Client", new String[] {
                "#l#" + String.format("%04d", client.getValue().getClientID()),
                "#l   ├─ Name: " + client.getValue().getFirstName() + " " + client.getValue().getLastName(),
                "#l   ├─ Phone: " + client.getValue().getPhone(),
                "#l   └─ CPF: " + ConversionUtils.cpfToString(client.getValue().getCPF())
        });
        return 0;
    }

    private static int Error() {
        MenuUtils.printWindow("Error", new String[]{
                "#cFailed to get client!",
        });
        return 0;
    }
}
