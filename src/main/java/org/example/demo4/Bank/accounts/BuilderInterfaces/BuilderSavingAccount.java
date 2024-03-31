package org.example.demo4.Bank.accounts.BuilderInterfaces;


import org.example.demo4.Bank.accounts.SavingAccount;


public class BuilderSavingAccount extends BuilderAccount{
    double minimumBalance = 0;
    double interestRate;


    public BuilderSavingAccount(String accountNumber, String accountHolderNumber, Double interestRate) {
        super(accountNumber, accountHolderNumber);
        this.interestRate = interestRate;
    }



    @Override
    public BuilderSavingAccount setBalance(Double balance) {
        super.balance = balance;
        return this;
    }
    @Override
    public SavingAccount build(){
        return new SavingAccount(this, interestRate);
    }



    public BuilderSavingAccount setMinimumBalance(Double minimumBalance){
        this.minimumBalance = minimumBalance;
        return this;
    }
}
