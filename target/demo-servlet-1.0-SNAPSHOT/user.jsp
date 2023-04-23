<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
</head>
<body>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listUser" items="${listUser}">
        <tr>
            <td>${listUser.ID}</td>
            <td>${listUser.name}</td>
            <td>${listUser.password}</td>
            <td>${listUser.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>