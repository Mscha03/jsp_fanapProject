package org.example.demo4.Bank.accounts;

import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;
import org.example.demo4.DataBase.DataBaseController;

import java.sql.SQLException;

public class CheckingAccount extends BankAccount {
    double overdraftLimit;

    public CheckingAccount(BuilderCheckingAccount builderCheckingAccount, Double overdraftLimit) {
        super(builderCheckingAccount);
        super.type = BankAccountType.CHECKING_ACCOUNT;
        this.overdraftLimit = overdraftLimit;
        DataBaseController.createAccountInDatabase(this);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, SQLException, InvalidTransactionException {
        if (amount > balance + overdraftLimit)
            throw new InsufficientFundsException("insufficient balance ");
        else if (amount < 0)
            throw new InsufficientFundsException("not valid amount ");
        else
            balance -= amount;
            DataBaseController.withdraw(this,amount);
        deductFees(amount);
    }

    @Override
    public void deposit(double amount) throws SQLException {
        super.deposit(amount);
        DataBaseController.deposit(this,amount);
        deductFees(amount);
    }

    public void deductFees(double amount) {
        balance -= ((amount * 5) / 100);
    }


    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
