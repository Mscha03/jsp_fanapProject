package org.example.demo4.Bank.accounts;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;
import org.example.demo4.DataBase.DataBaseController;

import java.io.Serializable;
import java.sql.SQLException;

public class BankAccount implements Serializable {
    protected String accountNumber;
    protected double balance = 0;
    protected String accountHolderName;
    public BankAccountType type;

    // Constructor
    public BankAccount(BuilderAccount builderAccount) {
        accountNumber = builderAccount.getAccountNumber();
        accountHolderName = builderAccount.getAccountHolderNumber();
        balance = builderAccount.getBalance();
    }

    // Account method
    public void deposit (double amount) throws SQLException {
        if (amount < 0)
            throw new IllegalArgumentException("amount of deposit can not be negative");
        else {
            balance += amount;
            DataBaseController.deposit(this, amount);
        }
    }

    public void withdraw (double amount) throws InsufficientFundsException, InvalidTransactionException, SQLException {
        if (amount > balance)
            throw new InsufficientFundsException("insufficient balance ");
        else if (amount < 0)
            throw new InsufficientFundsException("not valid amount ");
        else {
            balance -= amount;
            DataBaseController.withdraw(this, amount);
        }
    }


    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public BankAccountType getType() {
        return type;
    }

    public enum  BankAccountType {CHECKING_ACCOUNT, SAVING_ACCOUNT};
}
