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
    <title>Status List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>

<table class="table table-dark table-striped table-bordered">
    <thead class="thead">
    <tr>
        <th class="th">Id</th>
        <th class="th">Title</th>
        <th class="th">Description</th>
        <th class="th">Privacy</th>
        <th class="th">Location</th>
        <th class="th">Attachment Path</th>
        <th class="th">Update</th>
        <th class="th">Delete</th>
    </tr>
    </thead>
    <tbody class="tbody">
    <h4>Status(${statusList.size()})</h4>
    <c:forEach var="status" items="${statusList}">
        <tr>
            <td class="td">${status.getId()}</td>
            <td class="td">${status.getTitle()}</td>
            <td class="td">${status.getDescription()}</td>
            <td class="td">${status.getPrivacy()}</td>
            <td class="td">${status.getLocation().getLocationName()}</td>
            <td></td>
                <%--                <td class="td">${status.getAttachment().getAttachmentPath()}</td>--%>
            <td><a class="btn btn-success" href="#">Update</a></td>
            <td><a class="btn btn-danger" href="#">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
