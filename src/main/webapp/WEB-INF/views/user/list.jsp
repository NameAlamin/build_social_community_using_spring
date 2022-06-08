<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 5/30/2022
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<div class="container-fluid">
    <table class="table table-dark table-striped table-bordered">
        <thead class="thead">
        <tr>
            <th class="th">Id</th>
            <th class="th">Name</th>
            <th class="th">Email</th>
            <th class="th">Password</th>
            <th class="th">Location</th>
            <th class="th">Attachment Path</th>
            <th class="th">Update</th>
            <th class="th">Delete</th>


        </tr>
        </thead>
        <tbody class="tbody">
        <h4>Users(${userList.size()})</h4>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td class="td">${user.getId()}</td>
                <td class="td">${user.getName()}</td>
                <td class="td">${user.getEmail()}</td>
                <td class="td">${user.getPassword()}</td>
                <td class="td">${user.getLocation().getLocationName()}</td>
                <td class="td">${user.getAttachment().getAttachmentPath()}</td>
                <td><a class="btn btn-success" href="edit?id=${user.getId()}">Update</a></td>
                <td><a class="btn btn-danger" href="deleted?id=${user.getId()}">Delete</a></td>
<%--                userDao ai value expect korbe na pele all value null hisebe jabe--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
