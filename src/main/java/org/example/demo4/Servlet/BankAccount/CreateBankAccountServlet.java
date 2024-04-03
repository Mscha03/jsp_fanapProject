package org.example.demo4.Servlet.BankAccount;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderCheckingAccount;
import org.example.demo4.Bank.accounts.BuilderInterfaces.BuilderSavingAccount;
import org.example.demo4.Bank.accounts.CheckingAccount;
import org.example.demo4.Bank.accounts.SavingAccount;
import org.example.demo4.DataBase.AccountController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class CreateBankAccountServlet extends HttpServlet {

    private void createCheckingAccount(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        CheckingAccount checkingAccount = new BuilderCheckingAccount
                ( req.getParameter("AccountHolder"),
                        Double.parseDouble(req.getParameter("overdraft")))
                .setBalance(Double.parseDouble(req.getParameter("balance")))
                .build();

        AccountController.createAccountInDatabase(checkingAccount);
    }

    private void createSavingAccount(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        SavingAccount savingAccount = new BuilderSavingAccount
                ( req.getParameter("AccountHolder"), Double.parseDouble(req.getParameter("interest")))
                .setBalance(Double.parseDouble(req.getParameter("balance")))
                .setMinimumBalance(Double.parseDouble(req.getParameter("minimumBalance"))).build();

        AccountController.createAccountInDatabase(savingAccount);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String type = req.getParameter("AccountType");

        if (Objects.equals(type, "Checking")) {
            try {
                createCheckingAccount(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if (Objects.equals(type, "Saving")) {
            try {
                createSavingAccount(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect("success_create_account.jsp");
    }

}
