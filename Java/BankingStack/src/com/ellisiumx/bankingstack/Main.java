package com.ellisiumx.bankingstack;

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

    private boolean isRunning;
    private Command mainWindow;
    private Scanner consoleScanner;
    private DatabaseManager databaseManager;
    private List<Client> users;

    private void Run(String[] args) {
        this.isRunning = true;
        this.consoleScanner = new Scanner(System.in);
        this.mainWindow = new MainScreen(this);
        this.databaseManager = new DatabaseManager(Paths.get(System.getProperty("user.dir"), "db.bin"));
        try {
            this.databaseManager.Initialize();
            this.users = this.databaseManager.LoadUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommandLoop();
    }

    private void CommandLoop() {
        while(this.isRunning) {
            this.mainWindow.Run();
        }
    }

    public void Exit() { this.isRunning = false; }

    public Scanner getConsoleScanner() { return this.consoleScanner; }

    public DatabaseManager getDatabaseManager() { return this.databaseManager; }

    public List<Client> getUsers() { return this.users; }
}
