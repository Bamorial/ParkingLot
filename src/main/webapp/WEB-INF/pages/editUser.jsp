<%--
  Created by IntelliJ IDEA.
  User: veltanvlad
  Date: 11.12.2023
  Time: 08:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<t:pageTemplate pageTitle="ParkingLot">
  <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditUser">
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="username">username</label>
        <input type="text" class="form-control" id="username" name="usename"
               value="${user.username}" required>
        <div class="invalid-feedback">

        </div>
      </div>

      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="email">email</label>
          <input type="text" class="form-control" id="email" name="email" placeholder=""
                 value="${user.email}">
          <div class="invalid-feedback">
          </div>
        </div>

      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="password">password</label>
          <input type="text" class="form-control" id="password" name="password" placeholder=""
                 value="${user.password}">
          <div class="invalid-feedback">
          </div>
        </div>

      </div>
    </div>
    </div>
    <hr class="mb-4">
    <input type="hidden" name="id" value="${user.id}">
    <button type="submit">Save</button>
  </form>
</t:pageTemplate>