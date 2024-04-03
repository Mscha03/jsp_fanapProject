<%@ page import="java.util.List" %>
<%@ page import="org.example.demo4.Bank.accounts.BankAccount" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.example.demo4.Bank.accounts.CheckingAccount" %>
<%@ page import="org.example.demo4.Bank.accounts.SavingAccount" %>
<%@ page import="org.example.demo4.Bank.Transactions.Transaction" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Display</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        input[type="text"],
        input[type="password"],
        input[type="number"]
        {
            width: 100%;
            padding: 10px;
            margin: 8px -10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        button {
            width: 25%;
            margin: 10px;
            padding: 10px;
            background-color: #04AA6D;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        select{
            width: 100%;
            padding: 10px;
            background-color: #04AA6D;
            color: white;
            border: black;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #038855;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #04AA6D;
        }


    </style>

</head>
<body>
<div class="container">

    <%
        List<BankAccount> accounts = (List<BankAccount>) request.getAttribute("account");
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transaction");

    %>

    <form action="displayServlet" method="post">
        <select name="selectedOption" >
            <option value="account">Accounts</option>
            <option value="transaction">Transactions</option>
        </select>


        <input type="hidden" name="name" value="<%=request.getAttribute("GoToShowData")%>"/>

        <button type="submit">show</button>
    </form>


    <%
        String selectedOption = request.getParameter("selectedOption");
        if ("account".equals(selectedOption)) {
    %>

    <h2>Your account: </h2>
    <table>

        <tr>
            <th>account number</th>
            <th>balance</th>
            <th>type</th>
            <th>overdraft</th>
            <th>interest rate</th>
            <th>minimum balance</th>
        </tr>

        <%
            for (BankAccount account: accounts){
        %>

        <tr>
            <th><%= account.getAccountNumber()%></th>
            <th><%= account.getBalance()%></th>
            <th><%= account.getType()%></th>
            <%
                if (Objects.equals(account.getType().toString(), "CHECKING_ACCOUNT")){
            %>
            <th><%= ((CheckingAccount)account).getOverdraftLimit()%></th>
            <th></th>
            <th></th>
            <%
                }else if (Objects.equals(account.getType().toString(), "SAVING_ACCOUNT")){
            %>
            <th></th>
            <th><%= ((SavingAccount)account).getInterestRate()%></th>
            <th><%= ((SavingAccount)account).getMinimumBalance()%></th>
        </tr>
        <%
                }
            }
        %>
    </table>
    <%
    } else if ("transaction".equals(selectedOption)) {
    %>

    <h2>Your transaction</h2>
    <table >

        <tr>
            <th>transaction number</th>
            <th>account</th>
            <th>type</th>
            <th>amount</th>
        </tr>

        <%
            for (Transaction transaction : transactions){
        %>

        <tr>
            <th><%=transaction.getTransactionNumber()%></th>
            <th><%=transaction.getAccountNumber()%></th>
            <th><%=transaction.getTransactionType()%></th>
            <th><%=transaction.getAmount()%></th>
        </tr>

        <%
                }
            }

        %>
    </table>
</div>
</body>
</html>
