package org.example.demo4.Servlet.BankAccount;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.Bank.exeptions.InsufficientFundsException;
import org.example.demo4.Bank.exeptions.InvalidTransactionException;
import org.example.demo4.DataBase.AccountController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class TransferServlet extends HttpServlet {

    private static final String deposit = "Deposit";
    private static final String withdraw = "Withdraw";
    private static final String transfer = "Transfer";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("done.jsp");

        String transactionType = req.getParameter("TransactionType");
        switch (transactionType){
            case withdraw -> {
                String accountHolder = req.getParameter("AccountHolder");
                String accountNumber = req.getParameter("accountnumber");
                String amount = req.getParameter("amount");

                withdraw(accountNumber, accountHolder, amount);
                requestDispatcher.forward(req,resp);
            }
            case deposit -> {
                String accountHolder = req.getParameter("AccountHolder");
                String accountNumber = req.getParameter("accountnumber");
                String amount = req.getParameter("amount");

                deposit(accountNumber, accountHolder, amount);
                requestDispatcher.forward(req,resp);
            }
            case transfer -> {
                String accountHolder = req.getParameter("AccountHolder");
                String sourceAccountNumber = req.getParameter("sourceAccount");
                String destinationAccountNumber = req.getParameter("destinationAccount");
                String amount = req.getParameter("amount");
                requestDispatcher.forward(req,resp);

                try {
                    transfer(accountHolder, sourceAccountNumber, destinationAccountNumber, amount);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void transfer(String accountHolder, String sourceAccount, String destinationAccount, String amount) throws SQLException, ClassNotFoundException {
            BankAccount account = AccountController.getOneAccount(Integer.parseInt(sourceAccount));
            if (Objects.equals(accountHolder, account.getAccountHolderName())) {
                withdraw(sourceAccount, accountHolder, amount);
                BankAccount account2 = AccountController.getOneAccount(Integer.parseInt(destinationAccount));
                deposit(destinationAccount, account2.getAccountHolderName(), amount);
            }
    }

    private void deposit(String accountNumber, String accountHolder, String amount){
        try {
            BankAccount account = AccountController.getOneAccount(Integer.parseInt(accountNumber));
            if (Objects.equals(accountHolder, account.getAccountHolderName())){
                AccountController.deposit(account, Double.valueOf(amount));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void withdraw(String accountNumber, String accountHolder, String amount) {
        try {
            BankAccount account = AccountController.getOneAccount(Integer.parseInt(accountNumber));
            if (Objects.equals(accountHolder, account.getAccountHolderName())) {
                AccountController.withdraw(account, Double.valueOf(amount));
            }
        } catch (SQLException | ClassNotFoundException | InsufficientFundsException |
                 InvalidTransactionException e) {
            throw new RuntimeException(e);
        }
    }
}
