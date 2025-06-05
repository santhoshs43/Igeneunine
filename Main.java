

import java.util.*;

// Class representing a bank account
class BankAccount {
    private static int idCounter = 1000;
    private final int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String name, double initialDeposit) {
        this.accountHolderName = name;
        this.balance = initialDeposit;
        this.accountNumber = idCounter++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("===== ACCOUNT INFO =====");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        :Rs." + balance);
    }
}

// Main application class
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Integer, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. View Account Info");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositToAccount();
                case 3 -> viewAccount();
                case 4 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);
    }

    private static void createAccount() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double deposit = readDouble();

        BankAccount account = new BankAccount(name, deposit);
        accounts.put(account.getAccountNumber(), account);

        System.out.println("Account created successfully!");
        System.out.println("Your account number is: " + account.getAccountNumber());
    }

    private static void depositToAccount() {
        System.out.print("Enter account number: ");
        int accNum = readInt();

        BankAccount account = accounts.get(accNum);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = readDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAccount() {
        System.out.print("Enter account number: ");
        int accNum = readInt();

        BankAccount account = accounts.get(accNum);
        if (account != null) {
            account.displayAccountInfo();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Utility method to safely read an integer
    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }

    // Utility method to safely read a double
    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // discard invalid input
        }
        return scanner.nextDouble();
    }
}

