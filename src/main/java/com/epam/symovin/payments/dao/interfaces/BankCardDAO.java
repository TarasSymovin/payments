package com.epam.symovin.payments.dao.interfaces;

import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface BankCardDAO  {
    /**
     * <p>Get all bank card for user</p>
     * @param user card owner
     * @return all bank card for user
     */
    List<BankCard> getBankCardsForUser(User user);

    /**
     * <p>Get bank card by id</p>
     * @param id card id
     * @return bank card by id
     */
    BankCard getBankCard(int id);

    /**
     * <p>Get bank card by number</p>
     * @param number card number
     * @return bank card by number
     */
    BankCard getBankCardByNumber(String number);

    /**
     * <p>Set lock identifier for bank card</p>
     * @param bankCard bank card object
     * @param isBlock lock identifier
     * @return is lock identifier has been changed
     */
    boolean setCardActive(BankCard bankCard, boolean isBlock);

    /**
     * <p>Withdraw sum from bank card balance</p>
     * @param sum the sum which will be withdraw
     * @param bankCard bank card object
     * @return is the withdrawal was successful
     */
    boolean subtractBalance(BigDecimal sum, BankCard bankCard, Connection connection);

    /**
     * <p>Addition sum to the bank card balance</p>
     * @param sum the sum which will be add
     * @param bankCard bank card object
     * @param connection connection to database
     * @return is the add was successful
     */
    boolean addBalance(BigDecimal sum, BankCard bankCard, Connection connection);

    /**
     * <p>Get all bank card without information about payments</p>
     * @param id bank card id
     * @param connection connection to database
     * @return all payments for card
     */
    BankCard getBankCardWithoutPayments(int id);

    /**
     * <p>Add bank card to database</p>
     * @param bankCard bank card object
     * @return is bank card has been added
     */
    boolean addBankCard(BankCard bankCard);

    /**
     * <p>Set request status for unlock bank card</p>
     * @param bankCard bank card object
     * @param requestStatus request status for bank card
     * @return is the status has been changed
     */
    boolean setRequestStatus(BankCard bankCard, boolean requestStatus);

    /**
     * <p>Get bank cards which sent request to unlock</p>
     * @return bank cards which sent request
     */
    List<BankCard> getRequests();
}
