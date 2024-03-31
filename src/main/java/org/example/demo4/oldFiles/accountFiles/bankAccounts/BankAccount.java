package org.example.demo4.oldFiles.accountFiles.bankAccounts;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private final String accountNumber;
    protected double balance;
    private final String accountHolderName;


    public void deposit (double amount){
        if (amount < 0)
            throw new IllegalArgumentException("amount of deposit can not be negative");
        else
            balance += amount;
    }

    public void withdraw (double amount) throws InsufficientFundsException, InvalidTransactionException {
        if (amount > balance)
            throw new InsufficientFundsException("insufficient balance ");
        else if (amount < 0)
            throw new InsufficientFundsException("not valid amount ");
        else
            balance -= amount;
    }

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        balance = 0;
    }

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
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

}
