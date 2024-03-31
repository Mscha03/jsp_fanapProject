package org.example.demo4.Bank.accounts.BuilderInterfaces;

import org.example.demo4.Bank.accounts.CheckingAccount;

public class BuilderCheckingAccount extends BuilderAccount{
    double overDraftLimit;

    public BuilderCheckingAccount(String accountNumber, String accountHolderNumber, Double overDraftLimit) {
        super(accountNumber, accountHolderNumber);
        this.overDraftLimit = overDraftLimit;
    }

    @Override
    public BuilderCheckingAccount setBalance(Double balance) {
        super.balance = balance;
        return this;
    }

    @Override
    public CheckingAccount build() {
        return new CheckingAccount(this, overDraftLimit);
    }
}
