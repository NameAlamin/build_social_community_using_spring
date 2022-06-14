<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 6/6/22
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update Page</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<%--<%@include file="../../resources/js/header.ejs" %>--%>
<br>
<div class="container">
  <%--@elvariable id="statusDto" type="com.this_project.dto.StatusDto"--%>
  <form:form class="row g-3" method="POST" action="${pageContext.request.contextPath}/status/update"  modelAttribute="statusDto">
    <table class="table table-bordered table-dark">
      <tr>
        <td><form:input  path="id" placeholder="${statusDto.id}" readonly="true" class="form-control"></form:input></td>
      </tr>
      <tr>
          <td><label class="form-label">Title : </label></td>
          <td><form:input  path="title" placeholder="${statusDto.title}" class="form-control"></form:input></td>
      </tr>
      <tr>
          <td><label class="form-label">Description : </label></td>
          <td><form:input path="description" placeholder="${statusDto.description}" class="form-control"></form:input></td>
      </tr>



      <tr>
          <td>
            <label class="form-label" class="form-label">Privacy : </label>
          </td>
          <td><form:select id="inputState" class="form-select" path="privacy">
            <form:option  value="${statusDto.privacy}"/>
            <form:options items="${privacyList}"/>
          </form:select>
          </td>
      </tr>



      <tr>
          <td>
            <label class="form-label" class="form-label">Location : </label>
          </td>
            <%--                    <td><form:input path="location" />${user.location}</td>--%>
          <td><form:select id="inputState" class="form-select" path="location">
            <form:option  value="${statusDto.location}"/>
            <form:options items="${stringLocationList}"/>
          </form:select>
          </td>
      </tr>
      <tr>
          <td><input type="submit" class="btn btn-primary" value="save"/></td>
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>