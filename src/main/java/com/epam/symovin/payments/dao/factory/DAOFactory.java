package com.epam.symovin.payments.dao.factory;

import com.epam.symovin.payments.dao.interfaces.BankCardDAO;
import com.epam.symovin.payments.dao.interfaces.PaymentDAO;
import com.epam.symovin.payments.dao.interfaces.UserDAO;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory() {
        return new MySqlDAO();
    }

    public abstract UserDAO getUserDAO();
    public abstract BankCardDAO getBankCardDAO();
    public abstract PaymentDAO getPaymentDAO();
}
