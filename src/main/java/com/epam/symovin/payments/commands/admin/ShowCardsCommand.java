package com.epam.symovin.payments.commands.admin;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowCardsCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.ADMIN_CARD_PAGE, false);

        User user = DAOFactory.getDAOFactory().getUserDAO().getUser(Integer.parseInt(request.getParameter("user")));
        request.getSession().setAttribute("cards", user.getBankCards());
        request.getSession().setAttribute("cardUser", user);
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
