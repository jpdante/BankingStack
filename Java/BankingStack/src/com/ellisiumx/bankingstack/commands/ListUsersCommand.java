package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;

public class ListUsersCommand extends Command {
    public ListUsersCommand(Main programContext) {
        super(programContext);
    }

    @Override
    public int Run() {
        List<String> content = new ArrayList<>();
        for(Client user : this.programContext.getUsers()) {
            content.add("#l#" + String.format("%04d", user.getUserID()));
            content.add("#l   ├─ " + user.getFirstName() + " " + user.getLastName());
            content.add("#l   ├─ " + user.getPhone());
            content.add("#l   ├─ " + cpfToString(user.getCPF()));
            content.add("#l   └─ " + user.getPassword());
        }
        MenuUtils.printWindow("Users", content.toArray(new String[0]));
        return 0;
    }
}
