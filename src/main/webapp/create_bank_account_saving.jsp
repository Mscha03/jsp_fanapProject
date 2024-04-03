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

        input[type="text"],
        input[type="password"],
        input[type="number"]{
            width: 100%;
            padding: 10px;
            margin: 8px -10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #04AA6D;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #038855;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Enter your information</h1>
    <form action="bankAccountCreator" method="get">

        <input type="hidden" name="AccountHolder" value="<%=request.getParameter("createSaving")%>"/>

        <label for="balance">Initial balance:</label>
        <input type="number" id="balance" name="balance" value=0 placeholder="Enter your initial balance" required>

        <label for="interest">interest rate of account:</label>
        <input type="number" id="interest" name="interest" placeholder="Enter the interest of account" required>

        <label for="minimumBalance">minimum balance:</label>
        <input type="number" id="minimumBalance" name="minimumBalance" value=0 placeholder="Enter the minimum balance of account" required>

        <input type="hidden" name="AccountType" value="Saving">

        <button type="submit">Create account</button>
    </form>
</div>
</body>
</html>
