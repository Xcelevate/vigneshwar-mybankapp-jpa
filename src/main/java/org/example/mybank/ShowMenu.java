package org.example.mybank;

import org.example.mybank.entity.Account;
import org.example.mybank.services.AccountServices;
import org.example.mybank.services.LoginServices;

import java.util.Scanner;


public class ShowMenu {
    Scanner input = new Scanner(System.in);
    LoginServices loginServices = new LoginServices();
    AccountServices accountService = new AccountServices();

    public void entryPoint() {
        while (true) {
            try {
                if (!loginServices.isLoggedIn()) {
                    showAuthMenu();
                } else {
                    showMainMenu();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public void showAuthMenu() {
        System.out.println("\n1. Login\n2. Exit");
        System.out.print("Choice: ");
        String choice = input.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("UserID: ");
                String userId = input.nextLine();
                System.out.print("Password: ");
                String password = input.nextLine();

                if (loginServices.login(userId, password)) {
                    System.out.println("Login successful! Welcome, " + userId);
                } else {
                    System.out.println("Invalid credentials.");
                }
            }
            case "2" -> System.exit(0);
            default -> System.out.println("Invalid choice.");
        }
    }

    public void showMainMenu() throws Exception {
        System.out.println("mainmenu is working");
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. View My Accounts");
        System.out.println("2. Create New Account");
        System.out.println("3. View Balance");
        System.out.println("4. Deposit Money");
        System.out.println("5. Withdraw Money");
        System.out.println("6. Transfer Money");
        System.out.println("7. Logout");
        System.out.print("Selection: ");

        String choice = input.nextLine();

        switch (choice){
        case "1" -> accountService.listAccount();
        case "2" -> accountService.createAccount();
        case "3" -> accountService.viewBalance();
        }
    }

}
