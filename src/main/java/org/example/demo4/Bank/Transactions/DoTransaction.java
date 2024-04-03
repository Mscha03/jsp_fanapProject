package org.example.demo4.Bank.Transactions;

import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.DataBase.TransactionController;

import java.sql.SQLException;

public abstract class DoTransaction {
    public static void doIt(BankAccount account, Double amount, Transaction.TransactionType transactionType) throws SQLException {
        Transaction transaction = new Transaction(account, amount, transactionType);
        TransactionController.createTransactionInDatabase(transaction);
    }
}
