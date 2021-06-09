package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.BankCard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CardDetailCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Path path = null;

        BankCard bankCard = DAOFactory.getDAOFactory().getBankCardDAO().getBankCard(Integer.parseInt(request.getParameter("cardId")));

        if (bankCard != null){
            path = new Path(Path.PAYMENTS_MENU, false);
            session.setAttribute("card", bankCard);
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
