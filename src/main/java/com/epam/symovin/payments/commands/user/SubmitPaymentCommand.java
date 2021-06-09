package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.Payment;
import com.epam.symovin.payments.entities.User;
import com.epam.symovin.payments.services.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitPaymentCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Payment payment = DAOFactory.getDAOFactory().getPaymentDAO().getPayment(Integer.parseInt(request.getParameter("payment")), String.valueOf(request.getSession().getAttribute("locale")));
        Path path = new Path(Path.FAILED_PAGE, true);

        if (PaymentService.getInstance().submitPayment(payment)){
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("user", DAOFactory.getDAOFactory().getUserDAO().getUser(user.getLogin(), user.getPassword()));
            path = new Path(Path.SUCCESS_PAYMENT_PAGE, true);
        }

        return path;
    }
}
