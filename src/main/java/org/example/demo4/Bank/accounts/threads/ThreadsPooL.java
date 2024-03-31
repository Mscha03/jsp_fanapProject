package org.example.demo4.Bank.accounts.threads;

import org.example.demo4.Bank.accounts.BankAccount;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsPooL {
    public ThreadsPooL(int numberOfPools) {
        this.numberOfPools = numberOfPools;
    }

    protected int numberOfPools;
    protected double balanceSum;
    ExecutorService executor =  Executors.newFixedThreadPool(numberOfPools);
    public double sum (List<BankAccount> bankAccounts){
        executor.submit(() ->{
            for (BankAccount bankAccount : bankAccounts)
                balanceSum+= bankAccount.getBalance();
        });
        return balanceSum;
    }
}
