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
    <form action="deleteServlet" method="post">

        <input type="hidden" name="accountHolder" value="<%=request.getParameter("GoToDelete")%>"/>

        <label for="accountNumber">account number: </label>
        <input type="number" id="accountNumber" name="accountNumber" required>


        <button type="submit" style="background-color: crimson">Delete account</button>
    </form>
</div>
</body>
</html>
