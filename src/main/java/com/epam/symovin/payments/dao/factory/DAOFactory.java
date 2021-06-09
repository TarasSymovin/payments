package com.epam.symovin.payments.dao.factory;

import com.epam.symovin.payments.dao.interfaces.BankCardDAO;
import com.epam.symovin.payments.dao.interfaces.PaymentDAO;
import com.epam.symovin.payments.dao.interfaces.UserDAO;


public abstract class DAOFactory {
    /**
     * @return a DAO depending on its type
     */
    public static DAOFactory getDAOFactory() {
        return new MySqlDAO();
    }

    /**
     * @return DAO object for User
     */
    public abstract UserDAO getUserDAO();
    /**
     * @return DAO object for Bank card
     */
    public abstract BankCardDAO getBankCardDAO();
    /**
     * @return DAO object for Payments
     */
    public abstract PaymentDAO getPaymentDAO();
}
