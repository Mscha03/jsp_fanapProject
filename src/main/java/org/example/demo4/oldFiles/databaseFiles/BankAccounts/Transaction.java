package org.example.demo4.oldFiles.databaseFiles.BankAccounts;


import org.example.demo4.oldFiles.databaseFiles.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transaction {
    private int transactionNumber;
    private AccountRemoved account;
    private float amount;

    private TransactionType transactionType;

    private static final List<String> columns = Arrays.asList("TRANSACTIONNUMBER", "ACCOUNT", "AMOUNT");

    private static DataBaseConnector dataBaseConnector;

    public Transaction(AccountRemoved account, float amount , TransactionType transactionType) throws SQLException {

        transactionNumber = lastTransactionNumber() + 1;
        this.account = account;
        this.amount = amount;
        this.transactionType =transactionType;
        createTransactionInDatabase();
    }


    private void createTransactionInDatabase() {

        dataBaseConnector = new DataBaseConnector();

        List<String> sql = new ArrayList<>();
        sql.add(Integer.toString(transactionNumber));
        sql.add(Integer.toString(account.getId()));
        sql.add(Float.toString(amount));

        dataBaseConnector.insert("TRANSACTIONS", columns, sql);
    }

    private int lastTransactionNumber() throws SQLException {
        dataBaseConnector = new DataBaseConnector();

        ResultSet resultSet = dataBaseConnector.maximum("TRANSACTIONS", "TRANSACTIONNUMBER");
        if (resultSet.next()){
            return resultSet.getInt("MAX(TRANSACTIONNUMBER)");
        }
        return 0;
    }

    public enum  TransactionType {deposit, withdraw};


}
