package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.User;
import com.epam.symovin.payments.services.AddingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.symovin.payments.Path.CARD_MENU;

public class AddCardCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Path path = null;

        if (AddingService.getInstance().addBankCardForUser(user)){
            session.setAttribute("user", DAOFactory.getDAOFactory().getUserDAO().getUser(user.getLogin(), user.getPassword()));
            path = new Path(CARD_MENU, true);
        }

        session.setAttribute("currentPage", path);

        return path;
    }
}
