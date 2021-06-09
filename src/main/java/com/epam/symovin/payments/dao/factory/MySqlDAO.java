package com.epam.symovin.payments.dao.factory;

import com.epam.symovin.payments.dao.interfaces.BankCardDAO;
import com.epam.symovin.payments.dao.interfaces.PaymentDAO;
import com.epam.symovin.payments.dao.mysqldao.BankCardMySqlDAO;
import com.epam.symovin.payments.dao.mysqldao.PaymentMySqlDAO;
import com.epam.symovin.payments.dao.mysqldao.UserMySqlDAO;

public class MySqlDAO extends DAOFactory {
    private static volatile MySqlDAO mySqlDAO;

    public static MySqlDAO getInstance() {
        MySqlDAO localInstance = mySqlDAO;
        if (localInstance == null) {
            synchronized (MySqlDAO.class) {
                localInstance = mySqlDAO;
                if (localInstance == null) {
                    mySqlDAO = localInstance = new MySqlDAO();
                }
            }
        }
        return localInstance;
    }

    @Override
    public UserMySqlDAO getUserDAO() {
        return UserMySqlDAO.getInstance();
    }

    @Override
    public BankCardDAO getBankCardDAO() { return BankCardMySqlDAO.getInstance(); }

    @Override
    public PaymentDAO getPaymentDAO() {
        return PaymentMySqlDAO.getInstance();
    }
}
