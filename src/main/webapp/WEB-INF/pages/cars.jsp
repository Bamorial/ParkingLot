<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pageTemplate pageTitle="ParkingLot">
    <h1>Cars. Number of free spots: ${numberOfFreeSpots}</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Cars">
    <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary
btn-lg">Add Car</a>
    <button class="btn btn-danger" type="submit">Delete Cars</button>
    <div class="container text-center">
       <c:forEach var="car" items="${cars}">
            <div class="row">
                <div class="col">
                    <input type="checkbox" name="car_ids" value="${car.id}"/>
                </div>
                <div class="col">
                    ${car.licensePlate}
                </div>
                <div class="col">
                        ${car.parkingSpot}
                </div>
                <div class="col">
                        ${car.ownerName}
                </div>
                <div class="col">
                    <img src="${pageContext.request.contextPath}/CarPhotots?id=${car.id}" width="48">

                </div>
                <div class="col">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddCarPhoto?id=${car.id}" role="button">
                    Add Photo
                    </a>

                </div>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCar?id=${car.id}">Edit
                    Car</a>

            </div>

       </c:forEach>
        </div>

    </div>
    </form>

</t:pageTemplate>
