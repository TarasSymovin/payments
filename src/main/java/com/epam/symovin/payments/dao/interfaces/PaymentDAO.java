package com.epam.symovin.payments.dao.interfaces;

import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.Payment;
import com.epam.symovin.payments.entities.PaymentState;

import java.util.List;

public interface PaymentDAO {
    /**
     * <p>Get payment by id</p>
     * @param id payment id
     * @param locale user language code
     * @return payment by id
     */
    Payment getPayment(int id, String locale);

    /**
     * <p>Get all payment for bank card</p>
     * @param bankCard bank card which has the payments
     * @return bank card's payments
     */
    List<Payment> getPayments(BankCard bankCard);

    /**
     * <p>Make payment transaction</p>
     * @param payment payment object
     * @return is transfer has been success
     */
    boolean makeTransfer(Payment payment);

    /**
     * <p>Get payment state by id</p>
     * @param id payment id
     * @return payment state by id
     */
    PaymentState getPaymentState(int id);

    /**
     * <p>Create payment</p>
     * @param payment payment object
     * @return is payment has been created successful
     */
    boolean createPayment(Payment payment);
}
