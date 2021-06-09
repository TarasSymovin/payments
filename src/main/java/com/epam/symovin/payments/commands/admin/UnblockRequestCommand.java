package com.epam.symovin.payments.commands.admin;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UnblockRequestCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.REQUESTS_PAGE, false);

        BankCard bankCard = DAOFactory.getDAOFactory().getBankCardDAO().getBankCard(Integer.parseInt(request.getParameter("card")));
        DAOFactory.getDAOFactory().getBankCardDAO().setCardActive(bankCard, false);

        User user = DAOFactory.getDAOFactory().getUserDAO().getUser(bankCard.getUserId());

        request.getSession().setAttribute("cards", user.getBankCards());
        request.getSession().setAttribute("cardUser", user);

        List<User> users = (List<User>) request.getSession().getAttribute("users");

        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUserId() == user.getUserId()){
                users.set(i, user);
            }
        }

        request.getSession().setAttribute("users", users);
        request.getSession().setAttribute("requests", DAOFactory.getDAOFactory().getBankCardDAO().getRequests());
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
