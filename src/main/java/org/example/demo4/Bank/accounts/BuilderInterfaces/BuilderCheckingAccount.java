package org.example.demo4.Bank.accounts.BuilderInterfaces;

import org.example.demo4.Bank.accounts.CheckingAccount;

import java.sql.SQLException;

public class BuilderCheckingAccount extends BuilderAccount{
    double overDraftLimit;

    public BuilderCheckingAccount(String accountHolderName, Double overDraftLimit) throws SQLException {
        super(accountHolderName);
        this.overDraftLimit = overDraftLimit;
    }

    @Override
    public BuilderCheckingAccount setBalance(Double balance) {
        super.balance = balance;
        return this;
    }

    @Override
    public BuilderCheckingAccount setNumberAccount(Integer accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }


    @Override
    public CheckingAccount build() {
        return new CheckingAccount(this, overDraftLimit);
    }
}
