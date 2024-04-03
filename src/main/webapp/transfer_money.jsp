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
    <form action="transferBankAccountServlet" method="post">

        <input type="hidden" name="AccountHolder" value="<%=request.getParameter("GoToTransfer")%>"/>

        <input type="hidden" name="TransactionType" value="Transfer"/>

        <label for="sourceAccount">account number of your account: </label>
        <input type="number" id="sourceAccount" name="sourceAccount" required>

        <label for="destinationAccount">Destination account number: </label>
        <input type="number" id="destinationAccount" name="destinationAccount" required>

        <label for="amount">amount: </label>
        <input type="number" id="amount" name="amount" value=0 required>

        <button type="submit">transfer</button>
    </form>
</div>
</body>
</html>
