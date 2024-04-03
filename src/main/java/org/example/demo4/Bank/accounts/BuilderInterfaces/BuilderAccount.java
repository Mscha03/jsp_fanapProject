package org.example.demo4.Bank.accounts.BuilderInterfaces;

import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.DataBase.AccountController;

import java.sql.SQLException;

public abstract class BuilderAccount {
    protected int accountNumber;
    protected String accountHolderName;
    protected double balance = 0;

    public BuilderAccount(String accountHolderName) throws SQLException {
        this.accountHolderName = accountHolderName;
        accountNumber = lastBankAccountNumber() + 1;
    }


    public abstract BuilderAccount setBalance(Double balance);
    public abstract BuilderAccount setNumberAccount(Integer accountNumber);
    public abstract BankAccount build();

    private Integer lastBankAccountNumber() throws SQLException {
        return AccountController.getLastBankAccountNumber();
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public double getBalance() {
        return balance;
    }
}
