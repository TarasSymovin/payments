<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/card.css" %>
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=main">YouPay</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:if test="${locale == 'uk'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-ua"> </span><fmt:message key="admin_page_jsp.header.ua_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=en"><span class="flag-icon flag-icon-us"> </span> <fmt:message key="admin_page_jsp.header.en_language"/></a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${locale == 'en'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-us"></span><fmt:message key="admin_page_jsp.header.en_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=uk"><span class="flag-icon flag-icon-ua"> </span><fmt:message key="admin_page_jsp.header.ua_language"/></a>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="#">${user.userInfo.firstName} ${user.userInfo.lastName}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"><fmt:message key="label.total_balance"/> <ct:balance-sum user="${user}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="admin_page_jsp.header.logout"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br>
<div class="d-flex flex-row justify-content-center bd-highlight mb-3">
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${user.bankCards}">
        <c:set var="k" value="${k+1}"/>
        <a>
        <div class="container d-flex justify-content-center text-white mt-5">
                    <div class="card p-4">
                        <form id="selectCard" method="GET" action="${pageContext.request.contextPath}/controller">
                        <div class="card-top">
                                <input type="hidden" name="command" value="getCard">
                             <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6><fmt:message key="admin_card_page_jsp.table.header.balance"/></h6><img class="mb-1 img-fluid img-responsive card-image"
                                                     src="https://i.imgur.com/XN4Josy.png">
                            </div>
                            <div>
                                <input type="hidden" name="cardId" value="${item.cardId}">
                                <h5>${item.cardBalance}</h5>
                            </div>
                        </div>
                        <div class="card-bottom mt-4">
                            <div><span>${user.userInfo.firstName} ${user.userInfo.lastName}</span></div>
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6 id="cardNumber">${item.cardNumber}</h6>
                                <button class="btn btn-outline-info" type="submit"><fmt:message key="button.details"/></button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
        </a>
    </c:forEach>
    <c:if test="${(user.bankCards.size() lt 3) and (user.active eq true)}">
        <a href="controller?command=addCard">
            <div class="container d-flex justify-content-center text-white mt-5">
                <div class="card p-4">
                    <div class="card-top">
                        <h3><fmt:message key="button.add_card"/></h3>
                        <br>
                    </div>
                    <div class="card-bottom mt-4">
                        <br>
                        <h6><fmt:message key="label.add_card"/></h6>
                    </div>
                </div>
            </div>
        </a>
    </c:if>
</div>
