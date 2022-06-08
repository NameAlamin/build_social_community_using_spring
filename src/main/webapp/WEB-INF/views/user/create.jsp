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
<form:form action="${pageContext.request.contextPath}/user/store" method="post" modelAttribute="userDto">
    <div class="form-group">
        <label class="font-weight-bolder" for="name">Name</label>
        <form:input type="text" cssClass="form-control" id="name" placeholder="Name" path="name"/>
            <%--===================== akhane (path="name") ai name hoche amra UserDto class er
                object create korchi UserController ay ai boject er sathe mapping korachi
                form ta submit krle value gulo ai object ta collect korbe--%>
    </div>

    <div class="form-group">
        <label class="font-weight-bolder" for="email">Email address</label>
        <form:input type="email" cssClass="form-control" id="email" placeholder="Email" path="email"/>
    </div>

    <div class="form-group">
        <label class="font-weight-bolder" for="password">Password</label>
        <form:input type="password" cssClass="form-control" id="password" placeholder="Password" path="password"/>
    </div>

    <form:select cssClass="dropdown" path="location">
        <form:option selected="true" value="Select Location" disabled="true"/>
        <form:options items="${locationList}"/>
            <%--====================== locatonList asche Controller theke--%>
    </form:select>

    <br><br>

    <input type="file" name="image" accept="image/*"/>

    <button type="submit" class="btn btn-danger mt-4">Create</button>
</form:form>
</body>
</html>
