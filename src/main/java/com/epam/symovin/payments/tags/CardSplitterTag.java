package com.epam.symovin.payments.tags;

import com.epam.symovin.payments.entities.BankCard;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;

public class CardSplitterTag extends TagSupport {
    private String cardNumber;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int doStartTag() {
        if(cardNumber == null){
            return SKIP_BODY;
        }

        char delimiter = ' ';
        String result = cardNumber.replaceAll(".{4}(?!$)", "$0" + delimiter);

        try {
            pageContext.getOut().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
