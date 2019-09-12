package com.ellisiumx.bankingstack;

import com.ellisiumx.bankingstack.windows.MainWindow;
import com.ellisiumx.bankingstack.windows.Window;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().Run(args);
    }

    private boolean isRunning;
    private Window mainWindow;
    private Scanner consoleScanner;

    private void Run(String[] args) {
        this.isRunning = true;
        this.consoleScanner = new Scanner(System.in);
        this.mainWindow = new MainWindow(this);
        CommandLoop();
    }

    private void CommandLoop() {
        while(isRunning) {
            mainWindow.Run();
        }
    }

    public void Exit() { isRunning = false; }

    public Scanner getConsoleScanner() { return consoleScanner; }
}
