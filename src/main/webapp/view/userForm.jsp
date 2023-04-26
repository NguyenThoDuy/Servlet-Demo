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
        <a href="new">Add new User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="redirectHome">List All User</a>

    </h2>
</center>

<div align="center">
    <c:if test="${item != null}">
        <form action="update" method="post">
    </c:if>
    <c:if test="${item == null}">
        <form action="save" method="post">
    </c:if>
                  <table border="1" cellpadding="5">
                        <caption>
                            <h2>
                                <c:if test="${item != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${item == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${item != null}">
                            <input type="hidden" name="id" value="<c:out value='${item.id}' />"/>
                        </c:if>
                        <tr>
                            <th>Name:</th>
                            <td>
                                <input type="text" name="name" size="45"
                                       value="<c:out value='${item.name}' />"
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td>
                                <input type="email" name="email" size="45"
                                       value="<c:out value='${item.email}' />"
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>Age:</th>
                            <td>
                                <input type="number" name="age" size="45"
                                       value="<c:out value='${item.age}' />"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" value="Save"/>
                            </td>
                        </tr>
                 </table>
          </form>
</div>

</body>
</html>