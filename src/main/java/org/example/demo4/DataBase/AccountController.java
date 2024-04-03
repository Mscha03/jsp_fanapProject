package org.example.demo4.DataBase;

import org.example.demo4.Bank.Transactions.DoTransaction;
import org.example.demo4.Bank.Transactions.Transaction;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderSavingAccount;
import org.example.demo4.Bank.accounts.CheckingAccount;
import org.example.demo4.Bank.accounts.SavingAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;
import org.jetbrains.annotations.NotNull;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AccountController {

    private static List<String> columns;

    private static DataBaseConnector dataBaseConnector;

    public static void createAccountInDatabase( BankAccount account) {

        dataBaseConnector = new DataBaseConnector();
        List<String> sql = new ArrayList<>();


        if (account.getClass() == CheckingAccount.class) {
            columns = Arrays.asList("ACCOUNTHOLDERNAME", "BALANCE", "TYPEOFACCOUNT", "OVERDRAFTLIMIT");
            sql.add("'" + account.getAccountHolderName() + "'");
            sql.add(Double.toString(account.getBalance()));
            sql.add("'" + account.type.toString() + "'");
            sql.add(Double.toString(((CheckingAccount) account).getOverdraftLimit()));

        }else if (account.getClass() == SavingAccount.class){
            columns = Arrays.asList("ACCOUNTHOLDERNAME", "BALANCE", "TYPEOFACCOUNT", "INTERESTRATE", "MINIMUMBALANCE");
            sql.add("'" + account.getAccountHolderName() + "'");
            sql.add(Double.toString(account.getBalance()));
            sql.add("'" + account.type.toString() + "'");
            sql.add(Double.toString(((SavingAccount) account).getInterestRate()));
            sql.add(Double.toString(((SavingAccount) account).getMinimumBalance()));
        }

        dataBaseConnector.insert("ACCOUNTS", columns, sql, true);
    }


    public static BankAccount getOneAccount(int accountNumber) throws SQLException, ClassNotFoundException {
        dataBaseConnector = new DataBaseConnector();

        try {
            ResultSet resultSet = dataBaseConnector.find("ACCOUNTS", "ACCOUNTNUMBER",  String.valueOf(accountNumber) );

            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.CHECKING_ACCOUNT.toString())) {
                    return new BuilderCheckingAccount(
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("OVERDRAFTLIMIT"))
                            .setBalance(resultSet.getDouble("BALANCE"))
                            .setNumberAccount(resultSet.getInt("ACCOUNTNUMBER"))
                            .build();

                } else if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.SAVING_ACCOUNT.toString())) {
                    return new BuilderSavingAccount(
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("INTERESTRATE")
                    ).setBalance(resultSet.getDouble("BALANCE"))
                            .setNumberAccount(resultSet.getInt("ACCOUNTNUMBER"))
                            .setMinimumBalance(resultSet.getDouble("MINIMUMBALANCE"))
                            .build();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<BankAccount> getAccountsOfUser(String name){
        dataBaseConnector = new DataBaseConnector();

        List<BankAccount> listOfAccounts = new ArrayList<>();

        try {
            ResultSet resultSet = dataBaseConnector.getAll("ACCOUNTS", "ACCOUNTHOLDERNAME" ,name);

            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.CHECKING_ACCOUNT.toString())) {
                    listOfAccounts.add(new BuilderCheckingAccount(
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("OVERDRAFTLIMIT"))
                            .setBalance(resultSet.getDouble("BALANCE"))
                            .setNumberAccount(resultSet.getInt("ACCOUNTNUMBER"))
                            .build()
                    );
                } else if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.SAVING_ACCOUNT.toString())) {
                    listOfAccounts.add( new BuilderSavingAccount(
                                    resultSet.getString("ACCOUNTHOLDERNAME"),
                                    resultSet.getDouble("INTERESTRATE")
                            ).setBalance(resultSet.getDouble("BALANCE"))
                                    .setNumberAccount(resultSet.getInt("ACCOUNTNUMBER"))
                                    .setMinimumBalance(resultSet.getDouble("MINIMUMBALANCE"))
                                    .build()
                    );
                }
            }


            return listOfAccounts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteAccountFromDatabase(int accountNumber){
        dataBaseConnector = new DataBaseConnector();

        dataBaseConnector.delete("ACCOUNTS", "accountNumber" , Integer.toString(accountNumber));
    }


    public static void deposit(BankAccount account, Double amount) throws SQLException {
        if (amount < 0)
            throw new IllegalArgumentException("amount of deposit can not be negative");
        else {
            dataBaseConnector = new DataBaseConnector();

            dataBaseConnector.update("ACCOUNTS", "ACCOUNTNUMBER", String.valueOf(account.getAccountNumber()),
                    "BALANCE", Double.toString(account.getBalance() + amount));

            DoTransaction.doIt(account, amount, Transaction.TransactionType.deposit);
        }
    }

    public static void withdraw(BankAccount account, Double amount) throws InsufficientFundsException, InvalidTransactionException, SQLException {
        if (amount > account.getBalance())
            throw new InsufficientFundsException("insufficient balance ");
        else if (amount < 0)
            throw new InvalidTransactionException("not valid amount ");
        else {
            dataBaseConnector = new DataBaseConnector();

            dataBaseConnector.update("ACCOUNTS", "ACCOUNTNUMBER", String.valueOf(account.getAccountNumber()),
                    "BALANCE", Double.toString(account.getBalance() - amount));

            DoTransaction.doIt(account, amount, Transaction.TransactionType.withdraw);
        }
    }

    public static Integer getLastBankAccountNumber() throws SQLException {
        dataBaseConnector = new DataBaseConnector();

        ResultSet resultSet = dataBaseConnector.maximum("ACCOUNTS", "ACCOUNTNUMBER");
        if (resultSet.next()){
            return resultSet.getInt("MAX(ACCOUNTNUMBER)");
        }
        return 0;
    }

}
