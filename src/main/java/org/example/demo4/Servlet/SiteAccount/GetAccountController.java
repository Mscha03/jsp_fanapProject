package org.example.demo4.Servlet.SiteAccount;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo4.DataBase.PersonController;
import org.example.demo4.Person.Person;

import java.io.IOException;

public class GetAccountController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = null;
        try {

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            person = PersonController.getPersonDB( username, password);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (person != null) {
            req.setAttribute("requestedPerson", person);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("controllPanel.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
