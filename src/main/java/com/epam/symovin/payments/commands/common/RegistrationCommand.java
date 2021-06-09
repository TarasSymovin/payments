package com.epam.symovin.payments.commands.common;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.User;
import com.epam.symovin.payments.entities.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = new User();
        UserInfo userInfo = new UserInfo();

        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("pass"));

        userInfo.setFirstName(request.getParameter("firstName"));
        userInfo.setLastName(request.getParameter("lastName"));
        userInfo.setTaxPayerNumber(request.getParameter("taxNumber"));

        user.setUserInfo(userInfo);
        user.setUserType(DAOFactory.getDAOFactory().getUserDAO().getUserType(2));
        user.setActive(true);

        Path path = new Path(Path.CARD_MENU, false);

        if (DAOFactory.getDAOFactory().getUserDAO().addUser(user)){
            session.setAttribute("user", user);
            path = new Path(Path.CARD_MENU, false);
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
