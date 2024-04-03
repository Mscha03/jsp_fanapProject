package org.example.demo4.Servlet.BankAccount;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo4.Bank.accounts.BankAccount;
import org.example.demo4.DataBase.AccountController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String holder = req.getParameter("accountHolder");
        String accountNumber = req.getParameter("accountNumber");

        try {
            BankAccount account = AccountController.getOneAccount(Integer.parseInt(accountNumber));
            if (Objects.equals(holder, account.getAccountHolderName())) {
                AccountController.deleteAccountFromDatabase(Integer.parseInt(accountNumber));
                req.getRequestDispatcher("done.jsp").forward(req,resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
