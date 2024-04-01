package org.example.demo4;

import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderSavingAccount;
import org.example.demo4.Bank.accounts.CheckingAccount;
import org.example.demo4.Bank.accounts.SavingAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException, SQLException, InvalidTransactionException {

        CheckingAccount checkingAccount0 = new BuilderCheckingAccount("1","Mohammad",200.0)
                .setBalance(5000.0).build();
        SavingAccount savingAccount0 = new BuilderSavingAccount("2", "Ali", 5.0)
                .setMinimumBalance(500.0).setBalance(10000.0).build();

        checkingAccount0.withdraw(1000.0);
        savingAccount0.deposit(1000.0);


        System.out.println("done");

    }
}
