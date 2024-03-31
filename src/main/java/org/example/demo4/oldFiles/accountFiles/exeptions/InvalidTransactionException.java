package org.example.demo4.oldFiles.accountFiles.exeptions;

public class InvalidTransactionException extends Exception{
    public InvalidTransactionException(String message) {
        super(message);
    }
}
