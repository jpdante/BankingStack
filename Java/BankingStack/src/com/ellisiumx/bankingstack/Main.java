package com.ellisiumx.bankingstack;

import com.ellisiumx.bankingstack.manager.DatabaseManager;
import com.ellisiumx.bankingstack.model.User;
import com.ellisiumx.bankingstack.utils.VerificationUtils;
import com.ellisiumx.bankingstack.windows.MainWindow;
import com.ellisiumx.bankingstack.windows.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().Run(args);
    }

    private boolean isRunning;
    private Window mainWindow;
    private Scanner consoleScanner;
    private DatabaseManager databaseManager;
    private List<User> users;

    private void Run(String[] args) {
        this.isRunning = true;
        this.consoleScanner = new Scanner(System.in);
        this.mainWindow = new MainWindow(this);
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

    public List<User> getUsers() { return this.users; }
}
