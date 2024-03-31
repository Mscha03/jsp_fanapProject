package org.example.demo4.Bank.accounts.BuilderInterfaces;

import org.example.demo4.Bank.accounts.BankAccount;

public abstract class BuilderAccount {
    protected String accountNumber;
    protected String accountHolderNumber;
    protected double balance = 0;

    public BuilderAccount(String accountNumber, String accountHolderNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderNumber = accountHolderNumber;
    }


    public abstract BuilderAccount setBalance(Double balance);
    public abstract BankAccount build();



    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolderNumber() {
        return accountHolderNumber;
    }
    public double getBalance() {
        return balance;
    }
}
