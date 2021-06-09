package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainMenuCommand implements Command {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path path = new Path(Path.CARD_MENU, false);

        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", DAOFactory.getDAOFactory().getUserDAO().getUser(user.getLogin(), user.getPassword()));

        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
