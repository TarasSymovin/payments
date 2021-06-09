package com.epam.symovin.payments.dao.mysqldao;

import com.epam.symovin.payments.dao.factory.Connector;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.dao.interfaces.PaymentDAO;
import com.epam.symovin.payments.entities.*;
import com.epam.symovin.payments.sql.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMySqlDAO implements PaymentDAO {
    private static volatile PaymentMySqlDAO paymentMySqlDAO;

    public static PaymentMySqlDAO getInstance() {
        PaymentMySqlDAO localInstance = paymentMySqlDAO;
        if (localInstance == null) {
            synchronized (PaymentMySqlDAO.class) {
                localInstance = paymentMySqlDAO;
                if (localInstance == null) {
                    paymentMySqlDAO = localInstance = new PaymentMySqlDAO();
                }
            }
        }
        return localInstance;
    }

    private PaymentMySqlDAO() {
    }

    @Override
    public Payment getPayment(int id, String locale) {
        Payment payment = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentSQL.GET_PAYMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    payment = mapPayment(resultSet);
                    payment.setPaymentState(getPaymentState(payment.getPaymentStateId()));
                    payment.setPaymentType(getPaymentType(payment.getPaymentTypeId(), locale));
                    payment.setRecipientCard(DAOFactory.getDAOFactory().getBankCardDAO().getBankCard(payment.getRecipientCardId()));
                    payment.setSenderCard(DAOFactory.getDAOFactory().getBankCardDAO().getBankCard(payment.getSenderCardId()));

                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return payment;
    }

    @Override
    public List<Payment> getPayments(BankCard bankCard) {
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentSQL.GET_PAYMENTS)) {
            preparedStatement.setInt(1, bankCard.getCardId());
            preparedStatement.setInt(2, bankCard.getCardId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = mapPayment(resultSet);
                    payment.setPaymentState(getPaymentState(payment.getPaymentStateId()));
                    payment.setRecipientCard(DAOFactory.getDAOFactory().getBankCardDAO().getBankCardWithoutPayments(payment.getRecipientCardId()));
                    payment.setSenderCard(DAOFactory.getDAOFactory().getBankCardDAO().getBankCardWithoutPayments(payment.getSenderCardId()));
                    payments.add(payment);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return payments;
    }

    @Override
    public void makeTransfer(Payment payment) {
        Connection connection = Connector.getConnection();

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            DAOFactory.getDAOFactory().getBankCardDAO().subtractBalance(payment.getPaymentSum(), payment.getSenderCard());
            DAOFactory.getDAOFactory().getBankCardDAO().addBalance(payment.getPaymentSum(), payment.getRecipientCard());
            setPaymentState(payment, getPaymentState(1));

            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public void setPaymentState(Payment payment, PaymentState paymentState) {
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentSQL.SET_PAYMENT_STATE)) {
            preparedStatement.setInt(1, paymentState.getPaymentStateId());
            preparedStatement.setInt(2, payment.getPaymentId());

            if (preparedStatement.executeUpdate() > 0) {
                payment.setPaymentState(paymentState);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public PaymentState getPaymentState(int id) {
        PaymentState paymentState = new PaymentState();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentSQL.GET_PAYMENT_STATE)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paymentState = mapPaymentState(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return paymentState;
    }

    public PaymentType getPaymentType(int id, String locale) {
        PaymentType paymentType = new PaymentType();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentTypeSQL.GET_PAYMENT_TYPE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paymentType = mapPaymentType(resultSet);
                    paymentType.setPaymentName(getPaymentTypeName(paymentType.getPaymentTypeId(), getLocaleId(locale)));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return paymentType;
    }

    public String getPaymentTypeName(int typeId, int localeId) {
        String paymentTypeName = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentLocaleSQL.GET_PAYMENT_NAME)) {
            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, localeId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    paymentTypeName = resultSet.getString(PaymentLocaleSQL.PAYMENT_TYPE_NAME);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return paymentTypeName;
    }

    public int getLocaleId(String locale) {
        int id = 0;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LocaleSQL.GET_LOCALE_ID)) {
            preparedStatement.setString(1, locale);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(LocaleSQL.ID_LOCALE);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return id;
    }

    @Override
    public boolean createPayment(Payment payment) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PaymentSQL.CREATE_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBigDecimal(1, payment.getPaymentSum());
            preparedStatement.setString(2, payment.getDescription());
            preparedStatement.setInt(3, payment.getSenderCardId());
            preparedStatement.setInt(4, payment.getRecipientCardId());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        payment.setPaymentId(resultSet.getInt(1));
                        result = true;
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private Payment mapPayment(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();

        payment.setPaymentId(resultSet.getInt(PaymentSQL.PAYMENT_ID));
        payment.setPaymentSum(resultSet.getBigDecimal(PaymentSQL.PAYMENT_SUM));
        payment.setDescription(resultSet.getString(PaymentSQL.PAYMENT_DESCRIPTION));
        payment.setPaymentDateTime(resultSet.getTimestamp(PaymentSQL.PAYMENT_DATETIME));
        payment.setSenderCardId(resultSet.getInt(PaymentSQL.SENDER_CARD));
        payment.setRecipientCardId(resultSet.getInt(PaymentSQL.RECIPIENT_CARD));
        payment.setPaymentTypeId(resultSet.getInt(PaymentSQL.PAYMENT_TYPE));
        payment.setPaymentStateId(resultSet.getInt(PaymentSQL.PAYMENT_STATE));

        return payment;
    }

    private PaymentState mapPaymentState(ResultSet resultSet) throws SQLException {
        PaymentState paymentState = new PaymentState();

        paymentState.setPaymentStateId(resultSet.getInt(PaymentStateSQL.PAYMENT_STATE_ID));
        paymentState.setPaymentState(resultSet.getString(PaymentStateSQL.STATE_NAME));

        return paymentState;
    }

    private PaymentType mapPaymentType(ResultSet resultSet) throws SQLException {
        PaymentType paymentType = new PaymentType();

        paymentType.setPaymentTypeId(resultSet.getInt(PaymentTypeSQL.PAYMENT_TYPE_ID));
        paymentType.setCommission(resultSet.getBigDecimal(PaymentTypeSQL.PAYMENT_COMMISSION));

        return paymentType;
    }
}
