package org.example.demo4;

import org.example.demo4.Bank.Transactions.Transaction;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderSavingAccount;
import org.example.demo4.DataBase.AccountController;
import org.example.demo4.DataBase.DataBaseConnector;
import org.example.demo4.DataBase.TransactionController;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        List<BankAccount> accounts = AccountController.getAccountsOfUser("MohammadSaleh");
        Collection<String> b = accounts.stream().map(a -> {
            return "transaction number = " + a.getType();
        }).toList();
        System.out.println(b);
        /*Collection<Integer> a = accounts.stream().map(BankAccount::getAccountNumber).toList();

        List<Transaction> transactions = new ArrayList<>();

        for(Integer s: a){
            transactions.addAll(TransactionController.getTransactionOfAccount(s));
        }


        Collection<String> b = transactions.stream().map(transaction -> {
            return "transaction number = " + transaction.getTransactionNumber() + "\n" +
                    "account = " + transaction.getAccountNumber() + "\n" +
                            "amout = " + transaction.getAmount() + "\n" +
                            "type = " +transaction.getTransactionType().toString() + "\n\n\n";

        }).toList();
        System.out.println(b);*/

    }


}