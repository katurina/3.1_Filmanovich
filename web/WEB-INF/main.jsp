<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Response</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty requestScope.person.email}">
        <table border="1px">
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Number</th>
                <th>e-mail</th>
            </tr>
            <tr>
                <td>${requestScope.person.name}</td>
                <td>${requestScope.person.surname}</td>
                <td>${requestScope.person.number} </td>
                <td>${requestScope.person.email}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        No such person
    </c:otherwise>
</c:choose>
</body>
</html>
