package com.epam.symovin.payments.dao.interfaces;

import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.Payment;
import com.epam.symovin.payments.entities.PaymentState;

import java.util.List;

public interface PaymentDAO {
    Payment getPayment(int id, String locale);

    List<Payment> getPayments(BankCard bankCard);

    void makeTransfer(Payment payment);

    PaymentState getPaymentState(int id);

    boolean createPayment(Payment payment);
}
