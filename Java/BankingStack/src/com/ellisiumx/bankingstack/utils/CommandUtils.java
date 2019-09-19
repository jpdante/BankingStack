package com.ellisiumx.bankingstack.utils;

import com.ellisiumx.bankingstack.Main;
import com.ellisiumx.bankingstack.model.Account;
import com.ellisiumx.bankingstack.model.Client;
import javafx.util.Pair;

import java.util.Scanner;

public class CommandUtils {

    public static Pair<Boolean, String> GetFirstName(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("First Name: ");
            String data = scanner.nextLine();
            if (data.length() <= 0) {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid name&r",
                });
                if (!TryAgain(scanner)) return new Pair<>(false, null);
                continue;
            }
            return new Pair<>(true, data);
        }
        return new Pair<>(false, null);
    }

    public static Pair<Boolean, String> GetLastName(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("Last Name: ");
            String data = scanner.nextLine();
            if (data.length() <= 0) {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid name&r",
                });
                if (!TryAgain(scanner)) return new Pair<>(false, null);
                continue;
            }
            return new Pair<>(true, data);
        }
        return new Pair<>(false, null);
    }

    public static Pair<Boolean, String> GetPhone(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("Phone: ");
            String data = scanner.nextLine();
            if (data.length() <= 0 || !VerificationUtils.validatePhone(data)) {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid phone&r",
                        "Valid syntax:",
                        "&b+55 11 12345-6789&r",
                });
                if (!TryAgain(scanner)) return new Pair<>(false, null);
                continue;
            }
            return new Pair<>(true, data);
        }
        return new Pair<>(false, null);
    }

    public static Pair<Boolean, String> GetCPF(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("CPF: ");
            String data = scanner.nextLine();
            if (data.length() <= 0 || !VerificationUtils.validateCPF(data)) {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid cpf&r",
                });
                if (!TryAgain(scanner)) return new Pair<>(false, null);
                continue;
            }
            return new Pair<>(true, data);
        }
        return new Pair<>(false, null);
    }

    public static Pair<Boolean, Double> GetLimit(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("Limit: ");
            double limit = scanner.nextDouble();
            if (limit <= 0.0d) {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid limit&r",
                        "Limit must be greater than 0!",
                });
                if (!TryAgain(scanner)) return new Pair<>(false, 0.0d);
                continue;
            }
            return new Pair<>(true, limit);
        }
        return new Pair<>(false, 0.0d);
    }

    public static Pair<Boolean, Client> GetClient(Main context) {
        while (Main.IsRunning()) {
            System.out.print("Client ID or CPF: ");
            String data = context.getConsoleScanner().nextLine().replace(".", "").replace("-", "");
            if (VerificationUtils.validateCPF(data)) {
                if (!context.getClientManager().hasClientWithCPF(data)) {
                    MenuUtils.printWindow("Error", new String[]{
                            "#c&4Invalid ID&r",
                    });
                    if (!TryAgain(context.getConsoleScanner())) return new Pair<>(false, null);
                    continue;
                }
                return new Pair<>(true, context.getClientManager().getClientByCPF(data));
            }
            ConversionUtils.IntConversionResponse intConversion = ConversionUtils.stringToInt(data);
            if (intConversion.isSuccess()) {
                if (intConversion.getResult() < 0 || !context.getClientManager().hasClientWithID(intConversion.getResult())) {
                    MenuUtils.printWindow("Error", new String[]{
                            "#c&4Invalid ID&r",
                    });
                    if (!TryAgain(context.getConsoleScanner())) return new Pair<>(false, null);
                    continue;
                }
                return new Pair<>(true, context.getClientManager().getClientByID(intConversion.getResult()));
            } else {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid input&r",
                });
                if (!TryAgain(context.getConsoleScanner())) return new Pair<>(false, null);
                continue;
            }
        }
        return new Pair<>(false, null);
    }

    public static Pair<Boolean, Account> GetAccount(Main context) {
        while (Main.IsRunning()) {
            System.out.print("Account ID: ");
            int id = context.getConsoleScanner().nextInt();
            for(Client client : context.getClientManager().getClients()) {
                for(Account account : client.getAccounts()) {
                    if(account.getAccountID() == id) return new Pair<>(true, account);
                }
            }
            MenuUtils.printWindow("Error", new String[]{
                    "#c&4Invalid account id, please try again&r",
            });
            if(!TryAgain(context.getConsoleScanner())) return new Pair<>(false, null);
            continue;
        }
        return new Pair<>(true, null);
    }

    public static Pair<Boolean, Double> GetAmount(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("Amount: ");
            double limit = scanner.nextDouble();
            if (limit <= 0.0d) {
                MenuUtils.printWindow("Error", new String[]{
                        "#c&4Invalid limit&r",
                        "Amount must be greater than 0!",
                });
                if (!TryAgain(scanner)) return new Pair<>(false, 0.0d);
                continue;
            }
            return new Pair<>(true, limit);
        }
        return new Pair<>(false, 0.0d);
    }

    public static boolean TryAgain(Scanner scanner) {
        System.out.print("Try Again ? (y/n) ");
        char option = scanner.next().charAt(0);
        scanner.nextLine();
        switch (option) {
            case 'y':
            case 'Y':
                return true;
            default:
                return false;
        }
    }

    public static boolean GetSpecialAccount(Scanner scanner) {
        while (Main.IsRunning()) {
            System.out.print("Is Special Account ? (y/n) ");
            char option = scanner.next().charAt(0);
            scanner.nextLine();
            switch (option) {
                case 'y':
                case 'Y':
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

}
