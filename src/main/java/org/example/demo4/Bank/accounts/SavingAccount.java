package org.example.demo4.Bank.accounts;

import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;
import org.example.demo4.Bank.scheduler.Period;
import org.example.demo4.Bank.scheduler.Scheduler;
import org.example.demo4.DataBase.AccountController;

import java.sql.SQLException;

public class SavingAccount extends BankAccount {
    double interestRate;
    protected double minimumBalance;

    public SavingAccount(BuilderAccount builderAccount, Double interestRate) {
        super(builderAccount);
        super.type = BankAccountType.SAVING_ACCOUNT;
        this.interestRate = interestRate;
        /*applyInterestByTime();*/
    }


    public void applyInterest (){
        balance += (balance * (interestRate / 100));
    }

     @Override
     public void withdraw (double amount) throws InsufficientFundsException, InvalidTransactionException, SQLException {
         if (amount > balance)
             throw new InsufficientFundsException("insufficient balance ");
         else if (amount < 0)
             throw new InsufficientFundsException("not valid amount ");
         else if (balance - amount < minimumBalance)
             throw new InvalidTransactionException("your balance should maintain bigger than minimumBalance");
         else {
             balance -= amount;
             AccountController.withdraw(this,amount);
         }
     }

     private void applyInterestByTime() {
        Scheduler scheduler = new Scheduler(Period.Monthly) {
            @Override
            public void doOnTime() {
                applyInterest();
            }

        };
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}
