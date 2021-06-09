package com.epam.symovin.payments.tags;

import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;

public class BalanceSumTag extends TagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public int doStartTag() {
        if(user == null){
            return SKIP_BODY;
        }

        BigDecimal sum = user.getBankCards().stream()
                .map(BankCard::getCardBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        try {
            pageContext.getOut().write(sum.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
