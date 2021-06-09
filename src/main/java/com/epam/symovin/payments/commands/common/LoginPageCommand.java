package com.epam.symovin.payments.commands.common;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Path path = new Path(Path.PAGE_LOGIN, false);

        request.getSession().setAttribute("currentPage", path);

        return path;
    }
}
