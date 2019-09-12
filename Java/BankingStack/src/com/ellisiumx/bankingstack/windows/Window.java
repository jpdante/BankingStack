package com.ellisiumx.bankingstack.windows;

import com.ellisiumx.bankingstack.Main;

public abstract class Window {
    protected Main programContext;

    public Window (Main programContext) {
        this.programContext = programContext;
    }

    public abstract int Run();
}
