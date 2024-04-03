package org.example.demo4.Bank.Transactions;

import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.DataBase.TransactionController;

import java.sql.SQLException;

public class Transaction {
    private Double transactionNumber;
    BankAccount account;
    private Integer accountNumber;
    private Double amount;
    private TransactionType transactionType;



    public Transaction(BankAccount account, Double amount, TransactionType transactionType) throws SQLException {
        transactionNumber = TransactionController.lastTransactionNumber() + 1.0;
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transaction(Double transactionNumber, Integer accountNumber, Double amount, TransactionType transactionType) throws SQLException {
        this.transactionNumber = transactionNumber;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Double getTransactionNumber() {
        return transactionNumber;
    }

    public BankAccount getAccount() {
        return account;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public enum TransactionType{
        deposit,
        withdraw
    }

}