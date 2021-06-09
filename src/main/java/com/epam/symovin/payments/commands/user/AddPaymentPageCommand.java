package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPaymentPageCommand implements Command {

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new Path(Path.ADD_PAYMENT_PAGE, false);
    }
}
