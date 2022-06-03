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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-9">
            <%--@elvariable id="locationDto" type="com.dto.LocationDto"--%>
            <form:form action="${pageContext.request.contextPath}/location/store" method="post" modelAttribute="locationDto">

                <br><br>
                <label class="font-weight-bolder">Location:</label>
                <form:input cssClass="form-control" path="locationName"/>

                <button type="submit" class="btn btn-danger mt-4">Create</button>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
