package com.epam.symovin.payments.services;

import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.Payment;

public class PaymentService {
    private static volatile PaymentService paymentService;

    public static PaymentService getInstance() {
        PaymentService localInstance = paymentService;
        if (localInstance == null) {
            synchronized (PaymentService.class) {
                localInstance = paymentService;
                if (localInstance == null) {
                    paymentService = localInstance = new PaymentService();
                }
            }
        }
        return localInstance;
    }

    public boolean submitPayment(Payment payment){
        boolean result = false;

        if (payment.getPaymentSum().compareTo(payment.getSenderCard().getCardBalance()) <= 0){
            DAOFactory.getDAOFactory().getPaymentDAO().makeTransfer(payment);
            result = true;
        }

        return result;
    }

}
