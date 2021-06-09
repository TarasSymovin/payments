<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/card.css" %>
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
        <a class="navbar-brand" href="controller?command=main">YouPay</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:if test="${locale == 'uk'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
                                class="flag-icon flag-icon-ua"> </span><fmt:message
                                key="admin_page_jsp.header.ua_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=en"><span
                                    class="flag-icon flag-icon-us"> </span> <fmt:message
                                    key="admin_page_jsp.header.en_language"/></a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${locale == 'en'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="dropdown09" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><span
                                class="flag-icon flag-icon-us"></span><fmt:message
                                key="admin_page_jsp.header.en_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=uk"><span
                                    class="flag-icon flag-icon-ua"> </span><fmt:message
                                    key="admin_page_jsp.header.ua_language"/></a>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="#">${user.userInfo.firstName} ${user.userInfo.lastName}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
                            key="admin_page_jsp.header.logout"/></a>

                </li>
            </ul>
        </div>
    </div>
</nav>

<br>
<div class="d-flex flex-row justify-content-center bd-highlight mb-3">
    <a>
        <div class="container d-flex justify-content-center text-white mt-5">
            <div class="card p-4">
                <div class="card-top">
                    <div class="d-flex flex-row justify-content-between align-items-center">
                        <h6><fmt:message key="admin_card_page_jsp.table.header.balance"/></h6>
                    </div>
                    <div>
                        <h5>${card.cardBalance}</h5>
                    </div>
                </div>
                <div class="card-bottom mt-4">
                    <div><span>${user.userInfo.firstName} ${user.userInfo.lastName}</span></div>
                    <div class="d-flex flex-row justify-content-between align-items-center">
                        <h6 id="cardNumber">${card.cardNumber}</h6>
                        <c:if test="${user.active}">
                            <c:choose>
                                <c:when test="${card.block eq false}">
                                    <form name="blockCard" action="controller" method="post">
                                        <input type="hidden" name="command" value="userBlockCard">
                                        <input type="hidden" name="card" value="${card.cardId}">
                                        <button class="btn btn-outline-info" type="submit"><fmt:message
                                                key="button.block"/></button>
                                    </form>
                                </c:when>
                                <c:when test="${(card.block eq true) and (card.request eq false)}">
                                    <form name="sendRequest" method="post" action="controller">
                                        <input type="hidden" name="command" value="sendRequest">
                                        <input type="hidden" name="card" value="${card.cardId}">
                                        <button class="btn btn-outline-info" type="submit"><fmt:message
                                                key="button.unblock"/></button>
                                    </form>
                                </c:when>
                                <c:when test="${(card.block eq true) and (card.request eq true)}">
                                    <button class="btn btn-outline-info" type="submit"><fmt:message
                                            key="label.request_sent"/></button>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </a>
    <c:if test="${(card.block eq false) and (user.active eq true)}">
        <a href="controller?command=addPaymentPage">
            <div class="container d-flex justify-content-center text-white mt-5">
                <div class="card p-4">
                    <div class="card-top">
                        <h3><fmt:message key="button.create_payment"/></h3>
                        <br>
                    </div>
                    <div class="card-bottom mt-4">
                        <br>
                        <h6>-----------------------------------------------</h6>
                    </div>
                </div>
            </div>
        </a>
    </c:if>
</div>

<br>

<div class=container>
    <table id="example" cellspacing=0 class="table table-hover" width=100%>
        <thead>
        <tr>
            <th class="text-center" scope="col"><fmt:message key="payment_menu.table.header.payment_date"/></th>
            <th scope="col"><fmt:message key="payment_menu.table.header.payment_sum"/></th>
            <th scope="col"><fmt:message key="payment_menu.table.header.payment_status"/></th>
            <th class="text-center" scope="col"><fmt:message key="payment_menu.table.header.payment_recipient"/></th>
            <th class="text-center" scope="col"><fmt:message key="button.details"/></th>
        <tbody>
        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${card.payments}">
            <c:set var="k" value="${k+1}"/>
        <c:if test="${(item.recipientCard.cardNumber ne card.cardNumber) or (item.paymentState.paymentStateId eq 1)}">
        <tr>
            <td class="text-center">${item.paymentDateTime}</td>
            <td>${item.paymentSum}</td>
            <c:choose>
            <c:when test="${item.paymentState.paymentState eq 'Підготовлений' and locale eq 'en'}">
                <td>Ready</td>
            </c:when>
            <c:when test="${item.paymentState.paymentState eq 'Відправлений' and locale eq 'en'}">
                <td>Completed</td>
            </c:when>
            <c:when test="${locale eq 'uk'}">
                <td>${item.paymentState.paymentState}</td>
            </c:when>
            </c:choose>
            <td class="text-center">${item.recipientCard.cardNumber}</td>
            <td class="text-center">
                <form action="controller" name="submitPayment" method="get">
                    <input type="hidden" name="command" value="submitPaymentPage">
                    <input type="hidden" name="payment" value="${item.paymentId}">
                    <input type="hidden" name="locale" value="${locale}">
                    <button type="submit" class="btn btn-success"><fmt:message key="button.details"/></button>
                </form>
            </td>
            </c:if>
            </c:forEach>
    </table>
</div>

<script>
    $(document).ready(function () {
        $('#example').DataTable({
            <c:if test="${locale == 'uk'}">
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Ukranian.json"
            }
            </c:if>
        });
    });
</script>