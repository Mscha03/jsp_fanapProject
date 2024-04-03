package org.example.demo4.DataBase;

import org.example.demo4.Bank.Transactions.Transaction;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderSavingAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class TransactionController {

    private static final List<String> columns = Arrays.asList("TRANSACTIONNUMBER", "ACCOUNT", "AMOUNT", "TRANSACTIONTYPE");

    private static DataBaseConnector dataBaseConnector;

    public static void createTransactionInDatabase(Transaction transaction) {

        dataBaseConnector = new DataBaseConnector();

        List<String> sql = new ArrayList<>();
        sql.add(Double.toString(transaction.getTransactionNumber()));
        sql.add(String.valueOf(transaction.getAccount().getAccountNumber()));
        sql.add(Double.toString(transaction.getAmount()));
        sql.add("'" + transaction.getTransactionType().toString() + "'");

        dataBaseConnector.insert("TRANSACTIONS", columns, sql,false);
    }


    public static Double lastTransactionNumber() throws SQLException {
        dataBaseConnector = new DataBaseConnector();

        ResultSet resultSet = dataBaseConnector.maximum("TRANSACTIONS", "TRANSACTIONNUMBER");
        if (resultSet.next()){
            return resultSet.getDouble("MAX(TRANSACTIONNUMBER)");
        }
        return 0.0;
    }

    public static List<Transaction> getTransactionOfAccount(Integer accounts){
        dataBaseConnector = new DataBaseConnector();

        List<Transaction> listOfTransaction = new ArrayList<>();

        try {
            ResultSet resultSet = dataBaseConnector.getAll("TRANSACTIONS", "ACCOUNT" , String.valueOf(accounts));

            while (resultSet.next()) {
                listOfTransaction.add(
                        new Transaction(
                                resultSet.getDouble("TRANSACTIONNUMBER"),
                                resultSet.getInt("ACCOUNT"),
                                resultSet.getDouble("AMOUNT"),
                                Transaction.TransactionType.valueOf(resultSet.getString("TRANSACTIONTYPE"))
                        )
                );
            }

            return listOfTransaction;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
