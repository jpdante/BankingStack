package com.ellisiumx.bankingstack.commands;

import com.ellisiumx.bankingstack.Main;

public abstract class Command {
    protected Main programContext;

    public Command(Main programContext) {
        this.programContext = programContext;
    }

    public abstract int Run();
}
