package com.epam.symovin.payments.dao.interfaces;

import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface BankCardDAO  {
    List<BankCard> getBankCardsForUser(User user);

    BankCard getBankCard(int id);

    BankCard getBankCardByNumber(String number);

    boolean setCardActive(BankCard bankCard, boolean isBlock);

    boolean subtractBalance(BigDecimal sum, BankCard bankCard);

    boolean addBalance(BigDecimal sum, BankCard bankCard);

    BankCard getBankCardWithoutPayments(int id);

    boolean addBankCard(BankCard bankCard);

    boolean setRequestStatus(BankCard bankCard, boolean requestStatus);

    List<BankCard> getRequests();
}
