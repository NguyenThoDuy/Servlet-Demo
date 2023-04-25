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
        <th>Email</th>
        <th>Age</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listUser" items="${data}">
        <tr>
            <td>${listUser.id}</td>
            <td>${listUser.name}</td>
            <td>${listUser.email}</td>
            <td>${listUser.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>