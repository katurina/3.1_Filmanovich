<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Find guy</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller">
    Name:
    <input type="text" name="name"/><br>
    Surname:
    <input type="text" name="surname"/><br>
    <input type="submit" value="press me"/>
</form>
</body>
</html>
