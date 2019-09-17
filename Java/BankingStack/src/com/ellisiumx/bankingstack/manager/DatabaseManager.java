package com.ellisiumx.bankingstack.manager;

import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.model.SpecialAccount;
import com.ellisiumx.bankingstack.model.Client;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {

    private Path databasePath;
    private File databaseFile;
    private RandomAccessFile databaseRandomAccessFile;

    public DatabaseManager(Path databasePath) {
        this.databasePath = databasePath;
    }

    public void Initialize() throws IOException {
        databaseFile = new File(databasePath.toString());
        databaseRandomAccessFile = new RandomAccessFile(this.databaseFile, "rw");
        if(databaseFile.createNewFile()) {
            CreateDatabase();
        }
    }

    private void CreateDatabase() throws IOException {
        databaseRandomAccessFile.seek(0);
        databaseRandomAccessFile.writeInt(0);
    }

    public List<Client> LoadUsers() throws IOException {
        if(databaseRandomAccessFile.length() <= 0) CreateDatabase();
        databaseRandomAccessFile.seek(0);
        int quantity = databaseRandomAccessFile.readInt();
        if(quantity == 0) return new ArrayList<>();
        List<Client> users = new ArrayList<>(quantity);
        for(int i = 0; i < quantity; i++) {
            users.add(LoadUser());
        }
        return users;
    }

    private String ReadString(int size) throws IOException {
        byte[] buffer = new byte[size];
        int bytesRead = databaseRandomAccessFile.read(buffer, 0, size);
        return new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
    }

    private Client LoadUser() throws IOException {
        int userID = databaseRandomAccessFile.readInt();
        String firstName = ReadString(databaseRandomAccessFile.readInt());
        String lastName = ReadString(databaseRandomAccessFile.readInt());
        String phone = ReadString(databaseRandomAccessFile.readInt());
        String cpf = ReadString(databaseRandomAccessFile.readInt());
        String password = ReadString(databaseRandomAccessFile.readInt());
        Client user = new Client(userID, firstName, lastName, phone, cpf, password);
        int quantity = databaseRandomAccessFile.readInt();
        for(int i = 0; i < quantity; i++) {
            user.addAccount(LoadAccount(user));
        }
        return user;
    }

    private Account LoadAccount(Client user) throws IOException {
        byte accountType = databaseRandomAccessFile.readByte();
        int accountID = databaseRandomAccessFile.readInt();
        int creationDate = databaseRandomAccessFile.readInt();
        double balance = databaseRandomAccessFile.readDouble();
        if(accountType == 1) {
            return new Account(accountID, user, new Date((long)creationDate*1000), balance);
        } else if(accountType == 2) {
            double accountLimit = databaseRandomAccessFile.readDouble();
            return new SpecialAccount(accountID, user, new Date((long)creationDate*1000), balance, accountLimit);
        }
        return null;
    }

    private void SaveString(String data) throws IOException {
        byte[] buffer = data.getBytes(StandardCharsets.UTF_8);
        databaseRandomAccessFile.writeInt(buffer.length);
        databaseRandomAccessFile.write(buffer, 0, buffer.length);
    }

    public void SaveUsers(List<Client> users) throws IOException {
        databaseRandomAccessFile.seek(0);
        databaseRandomAccessFile.writeInt(users.size());
        for(Client user : users) {
            databaseRandomAccessFile.writeInt(user.getUserID());
            SaveString(user.getFirstName());
            SaveString(user.getLastName());
            SaveString(user.getPhone());
            SaveString(user.getCPF());
            SaveString(user.getPassword());
            databaseRandomAccessFile.writeInt(user.getAccounts().size());
            for(Account account : user.getAccounts()) {
                SaveAccount(account);
            }
        }
    }

    private void SaveAccount(Account account) throws IOException {
        byte accountType = 1;
        if(account instanceof SpecialAccount) accountType = 2;
        databaseRandomAccessFile.writeByte(accountType);
        databaseRandomAccessFile.writeInt(account.getAccountID());
        databaseRandomAccessFile.writeInt((int)(account.getCreationDate().getTime() / 1000L));
        databaseRandomAccessFile.writeDouble(account.getBalance());
        if(account instanceof SpecialAccount) databaseRandomAccessFile.writeDouble(((SpecialAccount)account).getAccountLimit());
    }
}
