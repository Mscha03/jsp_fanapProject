package org.example.demo4.Bank.exeptions;

public class InvalidTransactionException extends Exception{
    public InvalidTransactionException(String message) {
        super(message);
    }
}
