package org.example.demo4.oldFiles.accountFiles.bankAccounts.threads;

import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;

public class ThreadSynchronized {
    protected String accountNumber;
    protected String accountHolderName;
    protected double withdrawAmount;
    protected double depositAmount;
    public ThreadSynchronized(String accountNumber, String accountHolderName, double withdrawAmount, double depositAmount) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;

        this.withdrawAmount = withdrawAmount;
        this.depositAmount = depositAmount;
    }

    /*BankAccount bankAccount = new BankAccount(accountNumber, accountHolderName);
    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                thread1.start();
                synchronized (this) {bankAccount.withdraw(withdrawAmount);}

            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            } catch (InvalidTransactionException e) {
                throw new RuntimeException(e);
            }
        }
    });
    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            thread2.start();
            synchronized (this) {bankAccount.deposit(depositAmount);}

        }
    });*/
}
