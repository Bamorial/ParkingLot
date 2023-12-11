%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pageTemplate pageTitle="ParkingLot">
<form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddCar">
    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="license_plate">Lincense</label>
            <input id="license_plate" type="text" class="form-control" name="license_plate" required>
            <div class="invalid-feedback"></div>
            <label for="parking_spot">Parking Spot</label>
            <input id="parking_spot" type="text" class="form-control" name="parking_spot" required>
            <div class="invalid-feedback"></div>
            <label for="owner_id">Owner</label>
            <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                <option value="">Choose..</option>
            <c:forEach var="user" items="${users}" varStatus="required">
                <option value="${user.id}">${user.username}</option>
            </c:forEach>
            </select>
            <div class="invalid-feedback">Select</div>
        </div>
        <button type="submit">Save</button>

    </div>

</form>

</t:pageTemplate>
