<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>USER - APP </title>
</head>

<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="user/new">Add new User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="user">List All User</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="item" items="${data}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.email}</td>
                <td>${item.age}</td>
                <td>
                    <a href="user/detail?id=${item.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="user/delete?id=${item.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>