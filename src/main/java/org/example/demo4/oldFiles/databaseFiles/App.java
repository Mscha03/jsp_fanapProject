package org.example.demo4.oldFiles.databaseFiles;


import org.example.demo4.oldFiles.databaseFiles.BankAccounts.AccountRemoved;
import org.example.demo4.oldFiles.databaseFiles.exception.*;
import org.example.demo4.oldFiles.databaseFiles.exception.InvalidTransactionException;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, InvalidTransactionException, InsufficientFundsException {

        AccountRemoved.showOneAccount(9);
        AccountRemoved account = AccountRemoved.getOneAccount(9);
        account.deposit(500);
        AccountRemoved.showOneAccount(9);

        AccountRemoved.showOneAccount(3);
        AccountRemoved account1 = AccountRemoved.getOneAccount(3);
        account1.withdraw(700);
        AccountRemoved.showOneAccount(3);
    }
}
