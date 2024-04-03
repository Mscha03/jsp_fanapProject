<%@ page import="java.util.Objects" %>
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
<div class="container">
    <h1>Create an Account</h1>

    <form method="post" action="create_bank_account_checking.jsp">
        <input type="hidden" id="createChecking" name="createChecking" value="<%=request.getParameter("GoToChoose")%>" required>
        <button type="submit">create checking account</button>
    </form>

    <form method="post" action="create_bank_account_saving.jsp">
        <input type="hidden" id="createSaving" name="createSaving" value="<%=request.getParameter("GoToChoose")%>" required>
        <button type="submit">create saving account</button>
    </form>


</div>
</body>
</html>
