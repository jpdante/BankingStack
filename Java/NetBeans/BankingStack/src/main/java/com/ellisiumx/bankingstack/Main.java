package com.ellisiumx.bankingstack;

import com.ellisiumx.bankingstack.manager.ClientManager;
import com.ellisiumx.bankingstack.manager.DatabaseManager;
import com.ellisiumx.bankingstack.model.Client;
import com.ellisiumx.bankingstack.commands.MainScreen;
import com.ellisiumx.bankingstack.commands.Command;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().Run(args);
    }

    private static boolean isRunning;
    private Command mainWindow;
    private Scanner consoleScanner;
    private DatabaseManager databaseManager;
    private ClientManager clientManager;

    private void Run(String[] args) {
        isRunning = true;
        this.consoleScanner = new Scanner(System.in);
        this.mainWindow = new MainScreen(this);
        this.databaseManager = new DatabaseManager(Paths.get(System.getProperty("user.dir"), "db.bin"));
        this.clientManager = new ClientManager();
        try {
            this.databaseManager.Initialize();
            clientManager.addClients(this.databaseManager.LoadUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommandLoop();
    }

    private void CommandLoop() {
        while(isRunning) {
            this.mainWindow.Run();
        }
    }

    public void Exit() { isRunning = false; }

    public Scanner getConsoleScanner() { return this.consoleScanner; }

    public DatabaseManager getDatabaseManager() { return this.databaseManager; }

    public ClientManager getClientManager() { return this.clientManager; }

    public static boolean IsRunning() { return isRunning; }
}
