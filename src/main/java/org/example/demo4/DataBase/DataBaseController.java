package org.example.demo4.DataBase;


import org.example.demo4.Bank.Transaction;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderSavingAccount;
import org.example.demo4.Bank.accounts.CheckingAccount;
import org.example.demo4.Bank.accounts.SavingAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;

import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class  DataBaseController {

    private static  List<String> columns;

    private static DataBaseConnector dataBaseConnector;

    public static void createAccountInDatabase(BankAccount account) {

        dataBaseConnector = new DataBaseConnector();
        List<String> sql = new ArrayList<>();

        if (account.getClass() == CheckingAccount.class) {
            columns = Arrays.asList("ACCOUNTNUMBER", "ACCOUNTHOLDERNAME", "BALANCE", "TYPEOFACCOUNT", "OVERDRAFTLIMIT");
            sql.add(account.getAccountNumber());
            sql.add("'" + account.getAccountHolderName() + "'");
            sql.add(Double.toString(account.getBalance()));
            sql.add("'" + account.type.toString() + "'");
            sql.add(Double.toString(((CheckingAccount) account).getOverdraftLimit()));

        }else if (account.getClass() == SavingAccount.class){
            columns = Arrays.asList("ACCOUNTNUMBER", "ACCOUNTHOLDERNAME", "BALANCE", "TYPEOFACCOUNT", "INTERESTRATE", "MINIMUMBALANCE");
            sql.add(account.getAccountNumber());
            sql.add("'" + account.getAccountHolderName() + "'");
            sql.add(Double.toString(account.getBalance()));
            sql.add("'" + account.type.toString() + "'");
            sql.add(Double.toString(((SavingAccount) account).getInterestRate()));
            sql.add(Double.toString(((SavingAccount) account).getMinimumBalance()));
        }

        dataBaseConnector.insert("ACCOUNTS", columns, sql);
    }

    @Nullable
    public static BankAccount getOneAccount(int accountNumber) throws SQLException, ClassNotFoundException {
        dataBaseConnector = new DataBaseConnector();

        try {
            ResultSet resultSet = dataBaseConnector.find("ACCOUNTS", "ACCOUNTNUMBER", Integer.toString(accountNumber));

            if (resultSet.next()) {

                if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.CHECKING_ACCOUNT.toString())){
                    return new BuilderCheckingAccount(
                            Integer.toString(resultSet.getInt("ACCOUNTNUMBER")),
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("OVERDRAFTLIMIT")
                    ).setBalance(resultSet.getDouble("BALANCE"))
                            .build();
                } else if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.SAVING_ACCOUNT.toString())){
                    return new BuilderSavingAccount(
                            Integer.toString(resultSet.getInt("ACCOUNTNUMBER")),
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("INTERESTRATE")
                    ).setBalance(resultSet.getDouble("BALANCE"))
                            .setMinimumBalance(resultSet.getDouble("MINIMUMBALANCE"))
                            .build();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static List<BankAccount> getAllAccounts(){
        dataBaseConnector = new DataBaseConnector();

        List<BankAccount> listOfAccounts = new ArrayList<>();

        try {
            ResultSet resultSet = dataBaseConnector.getAll("ACCOUNTS");

            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.CHECKING_ACCOUNT.toString())){
                     listOfAccounts.add(
                             new BuilderCheckingAccount(
                            Integer.toString(resultSet.getInt("ACCOUNTNUMBER")),
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("OVERDRAFTLIMIT")
                    ).setBalance(resultSet.getDouble("BALANCE"))
                            .build()
                     );
                } else if (Objects.equals(resultSet.getString("TYPEOFACCOUNT"), BankAccount.BankAccountType.SAVING_ACCOUNT.toString())){
                     listOfAccounts.add(
                             new BuilderSavingAccount(
                            Integer.toString(resultSet.getInt("ACCOUNTNUMBER")),
                            resultSet.getString("ACCOUNTHOLDERNAME"),
                            resultSet.getDouble("INTERESTRATE")
                    ).setBalance(resultSet.getDouble("BALANCE"))
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

    public static void showOneAccount(int accountNumber) throws SQLException, ClassNotFoundException {
        dataBaseConnector = new DataBaseConnector();

        BankAccount account = getOneAccount(accountNumber);

        if (account != null) {
            System.out.println("[ number: " + account.getAccountNumber() +
                    "\t owner: " + account.getAccountHolderName() +
                    "\t balance: " + account.getBalance() +
                    "\t type: " + account.type.toString() +
                    " ]");
        }
    }

    public static void showAllAccounts() {
        List<BankAccount> accountList = getAllAccounts();
        accountList.stream()
                .map(account -> "number: " + account.getAccountNumber() +
                        "\t\t owner: " + account.getAccountHolderName() +
                        "\t\t balance: " + account.getBalance() +
                        "\t\t type: " + account.type.toString())
                .forEach(System.out::println);
    }

    public static void deposit(BankAccount account, Double amount) throws SQLException {
        if (amount < 0)
            throw new IllegalArgumentException("amount of deposit can not be negative");
        else {
            dataBaseConnector = new DataBaseConnector();

            dataBaseConnector.update("ACCOUNTS", "ACCOUNTNUMBER", account.getAccountNumber(),
                    "BALANCE", Double.toString(account.getBalance() + amount));

            Transaction transaction = new Transaction(account, amount, Transaction.TransactionType.deposit);
        }
    }

    public static void withdraw(BankAccount account, Double amount) throws InsufficientFundsException, InvalidTransactionException, SQLException {
        if (amount > account.getBalance())
            throw new InsufficientFundsException("insufficient balance ");
        else if (amount < 0)
            throw new InvalidTransactionException("not valid amount ");
        else {
            dataBaseConnector = new DataBaseConnector();

            dataBaseConnector.update("ACCOUNTS", "ACCOUNTNUMBER", account.getAccountNumber(),
                    "BALANCE", Double.toString(account.getBalance() - amount));

            Transaction transaction = new Transaction(account, amount, Transaction.TransactionType.withdraw);
        }
    }

}
