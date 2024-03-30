<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<c:set var="Amount" value="786.970"/>
<fmt:parseNumber var="j" type="number" value="${Amount}" integerOnly="false"/>
<p>
    <i>
        Amount is:
    </i>
    <c:out value="${j}"/>
</p>

<a href="index2.jsp">click</a>
</body>
</html>