package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitPageCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.SUBMIT_PAYMENT_PAGE, false);

        String locale = String.valueOf(request.getParameter("locale"));

        request.setAttribute("payment", DAOFactory.getDAOFactory().getPaymentDAO()
                .getPayment(Integer.parseInt(request.getParameter("payment")), locale));


        return path;
    }
}
