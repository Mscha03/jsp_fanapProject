package org.example.demo4.oldFiles.accountFiles.bankAccounts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank<T extends BankAccount> implements Serializable {

    HashMap<String, T> accounts;

    public Bank() throws IOException {

        File f = new File("AccountsSaveFile.txt");
        if (f.exists() && !f.isDirectory()) {
            accounts = fetchAccountsFromFile();
        } else {
            accounts = new HashMap<>();
            createFile();
        }
    }

    public void addAccount(T account) {
        accounts.put(account.getAccountNumber(), account);
        saveAccountsInFile();
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
        saveAccountsInFile();
    }

    public T findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<T> listAccounts() {
        List<T> accountsList = new ArrayList<>();
        for (HashMap.Entry mapElement : accounts.entrySet())
            accountsList.add((T) mapElement.getValue());
        return accountsList;
    }

    private void createFile() throws IOException {
        {
            Path newFilePath = Paths.get("AccountsSaveFile.txt");
            Files.createFile(newFilePath);
        }
    }

    private void saveAccountsInFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("AccountsSaveFile.txt")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listAccounts());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, T> fetchAccountsFromFile() {
        List<T> bankAccountList;

        try (FileInputStream fileInputStream = new FileInputStream("AccountsSaveFile.txt")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            bankAccountList = (List<T>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            // Handle the exceptions
            e.printStackTrace();
            bankAccountList = new ArrayList<>();
        }

        HashMap<String, T> accountHashMap = new HashMap<>();
        for (T bankAccount : bankAccountList) {
            accountHashMap.put(bankAccount.getAccountNumber(), bankAccount);
        }
        return accountHashMap;
    }

    public void printListAccounts() {
        accounts = fetchAccountsFromFile();
        listAccounts().stream()
                .map(
                        (account -> ("Account number: " + account.getAccountNumber() +
                                "\t Holder: " + account.getAccountHolderName() +
                                "\t Balance: " + account.getBalance())
                        )
                ).sorted()
                .forEach(System.out::println);

    }

    public void totalBalanceOfAccount(Double minimumBalance) {
        Double total = listAccounts().stream()
                .filter(account -> account.balance >= minimumBalance)
                .map(account -> account.balance)
                .reduce( 0.0, Double::sum);

        System.out.println("Total Balance of Account by minimum balance " +
                minimumBalance + ": " + total);

    }

    public void applyInterestManually(){
        listAccounts().stream()
                .filter(account -> account.getClass().equals(SavingAccount.class))
                .forEach(account -> ((SavingAccount) account).applyInterest());
        saveAccountsInFile();
    }
}

