package org.example.demo4.oldFiles.accountFiles.bankAccounts;

import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;
import org.example.demo4.Bank.scheduler.Period;
import org.example.demo4.Bank.scheduler.Scheduler;

public class SavingAccount extends BankAccount {
    final double interestRate;
    protected double minimumBalance;

    public SavingAccount(String accountNumber, String accountHolderName, double balance, double interestRate, double minimumBalance) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        applyInterestByTime();
    }

    public SavingAccount(String accountNumber, String accountHolderName, double interestRate) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
        applyInterestByTime();
    }

    public void applyInterest (){
        balance += (balance * (interestRate / 100));

    }
     @Override
     public void withdraw (double amount) throws InsufficientFundsException, InvalidTransactionException {
         if (amount > balance)
             throw new InsufficientFundsException("insufficient balance ");
         else if (amount < 0)
             throw new InsufficientFundsException("not valid amount ");
         else if (balance- amount < minimumBalance)
             throw new InvalidTransactionException("your balance should maintain bigger than minimumBalance");
         else
             balance -= amount;
     }

     private void applyInterestByTime() {
        Scheduler scheduler = new Scheduler(Period.Monthly) {
            @Override
            public void doOnTime() {
                applyInterest();
            }

        };
    }


}
