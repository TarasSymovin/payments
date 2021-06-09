package com.epam.symovin.payments.commands.admin;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowRequestsCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.REQUESTS_PAGE, false);

        request.getSession().setAttribute("requests", DAOFactory.getDAOFactory().getBankCardDAO().getRequests());
        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
