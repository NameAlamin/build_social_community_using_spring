<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 5/30/2022
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserForm</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<br>
<%--@elvariable id="userDto" type="com.dto.UserDto"--%>
<form:form action="${pageContext.request.contextPath}/user/update" method="post" modelAttribute="userDto">

    <div class="form-group">
        <form:input  path="id" placeholder="${userDto.id}" readonly="true" class="form-control"/>
    </div>

    <div class="form-group">
        <label class="font-weight-bolder" for="name">Name</label>
        <form:input type="text" cssClass="form-control" id="name" placeholder="${userDto.name}" path="name"/>
            <%--===================== akhane (path="name") ai name hoche amra UserDto class er
                object create korchi UserController ay ai boject er sathe mapping korachi
                form ta submit krle value gulo ai object ta collect korbe--%>
    </div>

    <div class="form-group">
        <label class="font-weight-bolder" for="email">Email address</label>
        <form:input type="email" cssClass="form-control" id="email" placeholder="${userDto.email}" path="email"/>
    </div>

    <div class="form-group">
        <label class="font-weight-bolder" for="password">Password</label>
        <form:input type="password" cssClass="form-control" id="password" placeholder="${userDto.password}" path="password"/>
    </div>

    <form:select cssClass="dropdown" path="location">
        <form:option selected="true" value="${userDto.location}" disabled="true"/>
        <form:options items="${stringLocationList}"/>
        <%--====================== locatonList asche Controller theke--%>
    </form:select>

    <br>

<%--    <input type="file" name="image" accept="image/*"/>--%>

    <button type="submit" class="btn btn-danger mt-4 ms-3">Update</button>
</form:form>
</body>
</html>
