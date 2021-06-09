package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.BankCard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendRequestCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.PAYMENTS_MENU, false);

        BankCard bankCard = DAOFactory.getDAOFactory().getBankCardDAO().getBankCard(Integer.parseInt(request.getParameter("card")));
        DAOFactory.getDAOFactory().getBankCardDAO().setRequestStatus(bankCard, true);

        request.getSession().setAttribute("card", bankCard);
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
