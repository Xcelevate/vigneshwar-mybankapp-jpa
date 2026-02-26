package org.example.mybank.services;

import org.example.mybank.Dao.AccountDao;
import org.example.mybank.entity.Account;
import org.example.mybank.exception.LoginRequiredException;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class AccountServices {
private final AccountDao accountDao = new AccountDao();
private final LoginServices loginServices = new LoginServices();


    // <<<------------------ listOfAccount ------------------------>>>


    public  void listAccount(){
        String userId = loginServices.getCurrentUser().getUserId();
        List<Account> userAcc = accountDao.getListOfAcc(userId);

        if(userAcc.isEmpty()){
            System.out.println("NO ACCOUNTS FOUND");
        }
        System.out.println("\n--- YOUR ACCOUNT ---");
        for(Account acc:userAcc){
            System.out.println(
                    "Account ID: " + acc.getAccountId() +
                            " | Account No: " + acc.getAccountNumber() +
                            " | Type: " + acc.getAccountType()
            );
        }
    }

    // <<<------------------ createAccount ------------------------>>>
    private static int accountSequence = 2004;
    private final Scanner input = new Scanner(System.in);



    public void createAccount() {
        if (loginServices.getCurrentUser() == null) throw new LoginRequiredException("Login required");

        System.out.print("Enter Account Type (SAVINGS / CURRENT / FIXED): ");
         String accountType = input.nextLine().toUpperCase();

         if(!accountType.equals("SAVINGS") && !accountType.equals("CURRENT") && !accountType.equals("FIXED")){
             System.out.println("Invalid account type");
             return;
         }
        System.out.println("Enter the initial amount: ");
         double amount = Double.parseDouble(input.nextLine());

         if(amount <= 500){
             System.out.println("initial amount should be minimum 500 ");
         }
         String accountNumber = "ACC" +accountSequence++;

         Account account = new Account();
         account.setAccountNumber(accountNumber);
         account.setAccountType(accountType);
         account.setBalance(amount);
         account.setUser(LoginServices.currentUser);

         accountDao.save(account);

        System.out.println("✅ Account created successfully!");
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Account No: " + accountNumber);

    }
    // <<<------------------ view balance ------------------------>>>

    public void viewBalance() {

        if (LoginServices.currentUser == null) {
            throw new LoginRequiredException("Login required to view balance");
        }

        listAccount();

        System.out.print("\nEnter Account Number: ");
        String accNo = input.nextLine();

        try {
            Double balance = accountDao.getBalance(accNo, LoginServices.currentUser.getUserId()).getBalance();
            System.out.println("Available Balance: " + balance);

        } catch (Exception e) {
            System.out.println("Account not found");
        }
    }


}
