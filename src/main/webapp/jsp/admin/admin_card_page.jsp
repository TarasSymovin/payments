<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../head.jsp"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=adminMenu">YouPay</a>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=showRequests"><fmt:message key="admin_page_jsp.header.request"/> (${requests.size()})</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">Admin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="admin_page_jsp.header.logout"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br>
<br>
<br>

<table class="table table-hover">
    <thead>
    <tr>
        <th class="text-center" scope="col"><fmt:message key="admin_card_page_jsp.table.header.card_number"/></th>
        <th scope="col"><fmt:message key="admin_card_page_jsp.table.header.owner"/></th>
        <th scope="col"><fmt:message key="admin_card_page_jsp.table.header.balance"/></th>
        <th class="text-center" scope="col"><fmt:message key="admin_card_page_jsp.table.header.iban"/></th>
        <th class="text-center" scope="col"><fmt:message key="admin_page_jsp.table.header.block"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${cards}">
    <c:set var="k" value="${k+1}"/>
    <tr>
        <td class="text-center">${item.cardNumber}</td>
        <td>${cardUser.userInfo.firstName} ${cardUser.userInfo.lastName}</td>
        <td>${item.cardBalance}</td>
        <td class="text-center">${item.cardIban}</td>
        <c:if test="${cardUser.active ne false}">
        <td class="text-center">
            <c:choose>
                <c:when test="${item.block eq false}">
                    <form name="blockUser" method="post" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="blockCard">
                        <input type="hidden" name="card" value="${item.cardId}">
                        <button type="submit" class="btn btn-outline-danger"><fmt:message key="button.block"/></button>
                    </form>
                </c:when>
                <c:when test="${item.block eq true}">
                    <form name="blockUser" method="post" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="unblockCard">
                        <input type="hidden" name="card" value="${item.cardId}">
                        <button type="submit" class="btn btn-outline-success"><fmt:message key="button.unblock"/></button>
                    </form>
                </c:when>
            </c:choose>
        </td>
        </c:if>
    </tr>
    </tbody>
    </c:forEach>
</table>

