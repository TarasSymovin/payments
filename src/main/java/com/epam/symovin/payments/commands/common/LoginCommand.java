package com.epam.symovin.payments.commands.common;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        Path path = new Path(Path.PAGE_LOGIN, true);

        User user = DAOFactory.getDAOFactory().getUserDAO().getUser(login, password);

        if (user != null){
            session.setAttribute("user", user);
            if (user.getUserType().getUserType().equals("user")){
                path = new Path(Path.CARD_MENU, false);
            }
            else if (user.getUserType().getUserType().equals("admin")){
                path = new Path(Path.ADMIN_MENU, false);
                session.setAttribute("users", DAOFactory.getDAOFactory().getUserDAO().getAllUsers());
                session.setAttribute("requests", DAOFactory.getDAOFactory().getBankCardDAO().getRequests());
            }
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
