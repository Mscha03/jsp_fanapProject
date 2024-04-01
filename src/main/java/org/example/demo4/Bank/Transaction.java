package org.example.demo4.Bank;


import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.DataBase.DataBaseConnector;
import org.example.demo4.DataBase.DataBaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transaction {
    private Double transactionNumber;
    BankAccount account;
    BankAccount.BankAccountType bankAccountType;
    private Double amount;
    private TransactionType transactionType;

    private static final List<String> columns = Arrays.asList("TRANSACTIONNUMBER", "ACCOUNT", "AMOUNT", "TRANSACTIONTYPE");

    private static DataBaseConnector dataBaseConnector;

    public Transaction(BankAccount account, Double amount , TransactionType transactionType) throws SQLException {

        transactionNumber = lastTransactionNumber() + 1.0;
        this.account = account;
        bankAccountType = account.getType();
        this.amount = amount;
        this.transactionType = transactionType;

        createTransactionInDatabase();
    }


    private void createTransactionInDatabase() {

        dataBaseConnector = new DataBaseConnector();

        List<String> sql = new ArrayList<>();
        sql.add(Double.toString(transactionNumber));
        sql.add(account.getAccountNumber());
        sql.add(Double.toString(amount));
        sql.add("'" + transactionType.toString() + "'");

        dataBaseConnector.insert("TRANSACTIONS", columns, sql);
    }

    private Double lastTransactionNumber() throws SQLException {
        dataBaseConnector = new DataBaseConnector();

        ResultSet resultSet = dataBaseConnector.maximum("TRANSACTIONS", "TRANSACTIONNUMBER");
        if (resultSet.next()){
            return resultSet.getDouble("MAX(TRANSACTIONNUMBER)");
        }
        return 0.0;
    }

    public enum  TransactionType {deposit, withdraw};
}
