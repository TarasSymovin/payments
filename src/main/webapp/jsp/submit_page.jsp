<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<c:import url="head.jsp"/>

<body style="margin: 0 !important; padding: 0 !important; background-color: #eeeeee;" bgcolor="#eeeeee">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td align="center" style="background-color: #eeeeee;" bgcolor="#eeeeee">
            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                <tr>
                    <td align="center" style="padding: 35px 35px 20px 35px; background-color: #ffffff;"
                        bgcolor="#ffffff">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%"
                               style="max-width:600px;">
                            <tr>
                                <td align="center"
                                    style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 15px;">
                                    <h2 style="font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;">
                                        <fmt:message key="submit_page.label.your_payment"/></h2>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" style="padding-top: 20px;">
                                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                        <tr>
                                            <td width="50%" align="left" bgcolor="#eeeeee"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                                <fmt:message key="submit_page.label.payment_number"/>
                                            </td>
                                            <td width="50%" align="left" bgcolor="#eeeeee"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                                ${payment.paymentId}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.payment_time"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${payment.paymentDateTime}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.sender_card"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <ct:card-split cardNumber="${payment.senderCard.cardNumber}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.sender"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${payment.senderCard.user.userInfo.firstName} ${payment.senderCard.user.userInfo.lastName}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
                                                <fmt:message key="submit_page.label.recipient_card"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
                                                <ct:card-split cardNumber="${payment.recipientCard.cardNumber}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.recipient"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${payment.recipientCard.user.userInfo.firstName} ${payment.recipientCard.user.userInfo.lastName}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="label.description"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${payment.description}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.payment_type"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${payment.paymentType.paymentName}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.commission"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${payment.paymentType.commission}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" style="padding-top: 20px;">
                                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                        <tr>
                                            <td width="75%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
                                                <fmt:message key="submit_page.label.total_sum"/>
                                            </td>
                                            <td width="25%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
                                                ${payment.paymentSum}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <c:if test="${(payment.paymentStateId eq 2) and (card.block eq false)}">
                    <tr>
                        <td align="center" style="border-radius: 5px;" bgcolor="#66b3b7"><a
                                href="${pageContext.request.contextPath}/controller?command=submitPayment&payment=${payment.paymentId}"
                                style="font-size: 18px; font-family: Open Sans, Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; border-radius: 5px; background-color: #5aa33c; padding: 15px 30px; border: 1px solid #5aa33c; display: block;"><fmt:message
                                key="button.submit_payment"/></a></td>
                    </tr>
                </c:if>
            </table>
        </td>
    </tr>
    <br>
    <br>
</table>
</body>