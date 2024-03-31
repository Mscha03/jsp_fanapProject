package org.example.demo4.Bank.accounts;

import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;

public class CheckingAccount extends BankAccount {
    double overdraftLimit;

    public CheckingAccount(BuilderCheckingAccount builderCheckingAccount, Double overdraftLimit) {
        super(builderCheckingAccount);
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


    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
