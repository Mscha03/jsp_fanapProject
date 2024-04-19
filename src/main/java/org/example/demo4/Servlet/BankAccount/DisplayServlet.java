package org.example.demo4.Servlet.BankAccount;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo4.Bank.Transactions.Transaction;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.DataBase.AccountController;
import org.example.demo4.DataBase.TransactionController;

import java.io.IOException;
import java.util.*;

public class DisplayServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("GoToDisplayServlet");

        List<BankAccount> accounts = AccountController.getAccountsOfUser(username);

        Collection<Integer> a = accounts.stream().map(BankAccount::getAccountNumber).toList();

        List<Transaction> transactions = new ArrayList<>();

        for (Integer s : a) {
            transactions.addAll(TransactionController.getTransactionOfAccount(s));
        }
        req.setAttribute("account", accounts);
        req.setAttribute("transaction", transactions);
        req.getRequestDispatcher("/showData.jsp").forward(req, resp);


    }
}
