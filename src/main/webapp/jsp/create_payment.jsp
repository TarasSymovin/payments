<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/create_payment.css" %>
</style>

<div class="card mt-50 mb-50">
    <div class="card-title mx-auto"><fmt:message key="button.create_payment"/></div>
    <form name="createPayment" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="addPayment">
        <input type="hidden" name=sentCard" value="${card.cardNumber}">
        <input type="hidden" name="locale" value="${locale}">
        <div class="row row-1">
            <div class="col-2"><img class="img-fluid" src="https://img.icons8.com/color/48/000000/visa.png" /></div>
            <div class="col-7">${card.cardNumber} ${locale}</div>
        </div>
        <div class="row row-1">
            <div class="col-2"><img class="img-fluid" src="https://img.icons8.com/color/48/000000/visa.png" /></div>
            <div class="col-7"> <input type="text" name="recipientCard" placeholder="<fmt:message key="payment_menu.table.header.payment_recipient"/>" required></div>
        </div>
        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"><fmt:message key="label.description"/></span> </div>
            <div class="row row-2"> <input type="text" name="paymentDescription" placeholder="<fmt:message key="label.description"/>"> </div>
        </div>
        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"><fmt:message key="payment_menu.table.header.payment_sum"/></span> </div>
            <div class="row row-2"> <input type="number" name="paymentSum" placeholder="<fmt:message key="payment_menu.table.header.payment_sum"/>" required> </div>
        </div>
        <button type="submit" class="btn d-flex mx-auto"><b><fmt:message key="button.create_payment"/></b></button>
    </form>
</div>
