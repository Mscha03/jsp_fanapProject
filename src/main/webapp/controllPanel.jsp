<%@ page import="org.example.demo4.Person.Person" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <style>
        /* Basic styling for the form */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #04AA6D;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        button:hover {
            background-color: #038855;
        }
    </style>
</head>
<body>
<%
    Object person = request.getAttribute("requestedPerson");
%>
<div class="container">
    <h1>Welcome <%=((Person)person).getPersonName() %></h1>

    <form method="post" action="choose_account_type.jsp">
        <input type="hidden" id="GoToChoose" name="GoToChoose" value="<%=((Person) person).getPersonName()%>" required>
        <button type="submit">create new bank account</button>
    </form>

    <form method="post" action="deposit.jsp">
        <input type="hidden" id="GoToDeposit" name="GoToDeposit" value="<%=((Person) person).getPersonName()%>" required>
        <button type="submit">deposit from bank account</button>
    </form>

    <form method="post" action="withdraw.jsp">
        <input type="hidden" id="GoToWithdraw" name="GoToWithdraw" value="<%=((Person) person).getPersonName()%>" required>
        <button type="submit">withdraw from bank account</button>
    </form>

    <form method="post" action="transfer_money.jsp">
        <input type="hidden" id="GoToTransfer" name="GoToTransfer" value="<%=((Person) person).getPersonName()%>" required>
        <button type="submit">Transfer money to another account</button>
    </form>

    <form method="post" action="displayServlet">
        <input type="hidden" id="GoToShowData" name="GoToDisplayServlet" value="<%=((Person) person).getPersonName()%>" required>
        <button type="submit">show data</button>
    </form>

    <form method="post" action="delete.jsp">
        <input type="hidden" id="GoToDelete" name="GoToDelete" value="<%=((Person) person).getPersonName()%>" required>
        <button type="submit">delete an account</button>
    </form>


</div>
</body>
</html>
