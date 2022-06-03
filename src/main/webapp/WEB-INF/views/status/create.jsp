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
            <br><br>
<%--            @elvariable id="status" type="com.fuad.model.StatusModel"--%>
            <form:form action="${pageContext.request.contextPath}/status/store" method="post" modelAttribute="statusDto">
                <div class="form-group">
                    <label class="font-weight-bolder" for="title">Title</label>
                    <form:input type="text" cssClass="form-control" id="title" placeholder="" path="title"/>
                </div>

                <div class="form-group">
                    <label class="font-weight-bolder" for="description">Description</label>
                    <form:textarea cssClass="form-control" path="description"/>
                </div>

                <form:select cssClass="dropdown" path="location">
                    <form:option selected="true" value="Select Location" disabled="true"/>
                    <form:options items="${locationList}"/>
                </form:select>

                <form:select cssClass="dropdown" path="privacy">
                    <form:option selected="true" value="Select Privacy" disabled="true"/>
                    <form:options items="${privacyList}"/>
                </form:select>

                <br>

                <%--<form:input type="file" name="profile_photo" placeholder="Photo" path="attachment" capture=""/>--%>

                <button type="submit" class="btn btn-danger mt-4">Create</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
