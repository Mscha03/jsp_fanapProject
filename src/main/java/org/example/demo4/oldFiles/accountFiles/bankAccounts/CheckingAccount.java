package org.example.demo4.oldFiles.accountFiles.bankAccounts;

import org.example.demo4.Bank.exeptions.InsufficientFundsException;

public class CheckingAccount extends BankAccount{

    double overdraftLimit;

    // Constructors
    public CheckingAccount(String accountNumber, String accountHolderName, double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(String accountNumber, String accountHolderName, double balance, double overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance + overdraftLimit)
            throw new InsufficientFundsException("insufficient balance ");
        else if (amount < 0)
            throw new InsufficientFundsException("not valid amount ");
        else
            balance -= amount;

        deductFees(amount);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        deductFees(amount);
    }

    public void deductFees(double amount) {
        balance -= ((amount * 5) / 100);
    }

}
