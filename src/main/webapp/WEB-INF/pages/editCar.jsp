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
<form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar">
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="license_plate">License Plate</label>
            <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder=""
                  value="${car.licensePlate}" required>
            <div class="invalid-feedback">
                License Plate is required.
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="parking_spot">Parking Spot</label>
                <input type="text" class="form-control" id="parking_spot" name="parking_spot" placeholder=""
                       value="${car.parkingSpot}">
                <div class="invalid-feedback">
                </div>
            </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <Label for="owner_id">Owner</label>
                        <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                            <option value="">Choose...</option>
                            <c:forEach var="user" items="${users}" varStatus="status">
                            <option value="${user.id}" ${car.ownerName eq user.username ? 'selected' : ''}>${user.username}</option>
                            </c:forEach>
                        </select>

                        </div>
                    </div>
                </div>
        </div>
    </div>
                     <hr class="mb-4">
                    <input type="hidden" name="car_id" value="${car.id}">
    <button type="submit">Save</button>
</form>
</t:pageTemplate>