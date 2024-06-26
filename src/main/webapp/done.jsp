<style>
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
    input[type="number"]
    {
        width: 100%;
        padding: 10px;
        margin: 8px -10px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
    button {
        width: 100%;
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


<html>
<body>
<div class="container">
    <h1>Done...</h1>
    <h2>Do you have another request?</h2>
    <button onclick="window.location.href = 'enter_account.jsp'">Click to back to login page</button>
</div>
</body>
</html>