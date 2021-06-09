package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.Payment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AddPayment implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.SUBMIT_PAYMENT_PAGE, true);
        Payment payment = new Payment();

        BankCard sentCard = (BankCard) request.getSession().getAttribute("card");
        BankCard recipientCard = DAOFactory.getDAOFactory().getBankCardDAO().getBankCardByNumber(request.getParameter("recipientCard"));

        if (recipientCard == null){
            return new Path(Path.FAILED_PAGE, true);
        }

        payment.setPaymentSum(BigDecimal.valueOf(Long.parseLong(request.getParameter("paymentSum"))));
        payment.setDescription(request.getParameter("paymentDescription"));
        payment.setSenderCardId(sentCard.getCardId());
        payment.setRecipientCardId(recipientCard.getCardId());

        if (DAOFactory.getDAOFactory().getPaymentDAO().createPayment(payment)){
            payment = DAOFactory.getDAOFactory().getPaymentDAO().getPayment(payment.getPaymentId(), request.getParameter("locale"));
            request.getSession().setAttribute("payment", payment);
        }

        return path;
    }
}
