<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pageTemplate pageTitle="ParkingLot">
    <h1>Users</h1>
    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">

    </c:if>
    <form method="POST" action="${pageContext.request.contextPath}/Users">
    <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-primary
btn-lg">Add User</a>
        <button type="submit" class="btn btn-primary btn-lg">Invoice</button>
    <div class="container text-center">

        <c:forEach var="user" items="${users}">
            <div class="row">
                <input type="checkbox" name="user_ids" value="${user.id}"  class="col mt-2"/>


                <div class="col">
                        ${user.username}
                </div>
                <div class="col">
                        ${user.email}
                </div>


            </div>

        </c:forEach>
    </div>

    </div>
    </form>
    <c:if test="${not empty invoices}">
        <h2>Invoices</h2>
        <c:forEach var="username" items="${invoices}" varStatus="status">
                ${status.index + 1}. ${username}
            <br/>
        </c:forEach>
    </c:if>
</t:pageTemplate>
