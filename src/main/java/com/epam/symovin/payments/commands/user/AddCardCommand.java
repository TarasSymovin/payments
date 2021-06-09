package com.epam.symovin.payments.commands.user;

import com.epam.symovin.payments.Path;
import com.epam.symovin.payments.commands.Command;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;
import org.iban4j.Iban;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

import static com.epam.symovin.payments.Path.CARD_MENU;

public class AddCardCommand implements Command {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Path path = null;

        BankCard bankCard = new BankCard();
        bankCard.setCardNumber(generate(16));
        bankCard.setCardIban(Iban.random().toString());
        bankCard.setUserId(user.getUserId());

        if (DAOFactory.getDAOFactory().getBankCardDAO().addBankCard(bankCard)){
            session.setAttribute("user", DAOFactory.getDAOFactory().getUserDAO().getUser(user.getLogin(), user.getPassword()));
            path = new Path(CARD_MENU, true);
        }

        session.setAttribute("currentPage", path);

        return path;
    }

    public String generate(int length) {
        Random random = new Random(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }

        return builder.toString();
    }
}
