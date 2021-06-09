package com.epam.symovin.payments.dao.mysqldao;

import com.epam.symovin.payments.dao.factory.Connector;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.dao.interfaces.BankCardDAO;
import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;
import com.epam.symovin.payments.sql.BankCardSQL;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;;

public class BankCardMySqlDAO implements BankCardDAO {
    private static volatile BankCardMySqlDAO bankCardMySqlDAO;

    public static BankCardMySqlDAO getInstance() {
        BankCardMySqlDAO localInstance = bankCardMySqlDAO;
        if (localInstance == null) {
            synchronized (BankCardMySqlDAO.class) {
                localInstance = bankCardMySqlDAO;
                if (localInstance == null) {
                    bankCardMySqlDAO = localInstance = new BankCardMySqlDAO();
                }
            }
        }
        return localInstance;
    }

    private BankCardMySqlDAO() {
    }

    @Override
    public List<BankCard> getBankCardsForUser(User user) {
        List<BankCard> bankCardList = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.GET_BANKCARD_FOR_USER)) {
            preparedStatement.setInt(1, user.getUserId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    BankCard bankCard = mapBankCard(resultSet);
                    bankCard.setPayments(DAOFactory.getDAOFactory().getPaymentDAO().getPayments(bankCard));
                    bankCardList.add(bankCard);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bankCardList;
    }

    @Override
    public BankCard getBankCard(int id) {
        BankCard bankCard = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.GET_BANKCARD)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bankCard = mapBankCard(resultSet);
                    bankCard.setPayments(DAOFactory.getDAOFactory().getPaymentDAO().getPayments(bankCard));
                    bankCard.setUser(DAOFactory.getDAOFactory().getUserDAO().getUser(bankCard.getUserId()));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bankCard;
    }

    @Override
    public BankCard getBankCardByNumber(String number) {
        BankCard bankCard = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.GET_BANKCARD_BY_NUMBER)) {
            preparedStatement.setString(1, number);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bankCard = mapBankCard(resultSet);
                    bankCard.setPayments(DAOFactory.getDAOFactory().getPaymentDAO().getPayments(bankCard));
                    bankCard.setUser(DAOFactory.getDAOFactory().getUserDAO().getUser(bankCard.getUserId()));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bankCard;
    }

    @Override
    public boolean setCardActive(BankCard bankCard, boolean isBlock) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.BLOCK_CARD)) {
            preparedStatement.setBoolean(1, isBlock);
            preparedStatement.setInt(2, bankCard.getCardId());

            if (preparedStatement.executeUpdate() > 0) {
                bankCard.setBlock(isBlock);
                result = true;
            }

            if (!isBlock){
                setRequestStatus(bankCard, false);
                bankCard.setRequest(false);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean subtractBalance(BigDecimal sum, BankCard bankCard, Connection connection) {
        boolean result = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.SUBTRACT_BALANCE)) {
            preparedStatement.setBigDecimal(1, sum);
            preparedStatement.setString(2, bankCard.getCardNumber());

            if (preparedStatement.executeUpdate() > 0){
                result = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addBalance(BigDecimal sum, BankCard bankCard, Connection connection) {
        boolean result = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.ADD_BALANCE)) {
            preparedStatement.setBigDecimal(1, sum);
            preparedStatement.setString(2, bankCard.getCardNumber());

            if (preparedStatement.executeUpdate() > 0){
                result = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public BankCard getBankCardWithoutPayments(int id) {
        BankCard bankCard = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.GET_BANKCARD)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bankCard = mapBankCard(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bankCard;
    }

    @Override
    public boolean addBankCard(BankCard bankCard) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(BankCardSQL.ADD_BANKCARD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bankCard.getCardNumber());
            preparedStatement.setString(2, bankCard.getCardIban());
            preparedStatement.setInt(3, bankCard.getUserId());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        bankCard.setUserId(resultSet.getInt(1));
                        result = true;
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean setRequestStatus(BankCard bankCard, boolean requestStatus){
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.SET_REQUEST_STATUS)) {
            preparedStatement.setBoolean(1, requestStatus);
            preparedStatement.setInt(2, bankCard.getCardId());

            if (preparedStatement.executeUpdate() > 0) {
                result = true;
                bankCard.setRequest(requestStatus);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public List<BankCard> getRequests(){
        List<BankCard> bankCards = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BankCardSQL.GET_REQUESTS)) {
            preparedStatement.setBoolean(1, true);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    BankCard bankCard = mapBankCard(resultSet);
                    bankCard.setPayments(DAOFactory.getDAOFactory().getPaymentDAO().getPayments(bankCard));
                    bankCard.setUser(DAOFactory.getDAOFactory().getUserDAO().getUser(bankCard.getUserId()));
                    bankCards.add(bankCard);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bankCards;
    }

    private BankCard mapBankCard(ResultSet resultSet) throws SQLException {
        BankCard bankCard = new BankCard();

        bankCard.setCardId(resultSet.getInt(BankCardSQL.CARD_ID));
        bankCard.setCardNumber(resultSet.getString(BankCardSQL.CARD_NUMBER));
        bankCard.setCardIban(resultSet.getString(BankCardSQL.CARD_IBAN));
        bankCard.setCardBalance(resultSet.getBigDecimal(BankCardSQL.CARD_BALANCE));
        bankCard.setCreditLimitCurrent(resultSet.getBigDecimal(BankCardSQL.CREDIT_LIMIT_CURRENT));
        bankCard.setCreditLimitAvailable(resultSet.getBigDecimal(BankCardSQL.CREDIT_LIMIT_AVAILABLE));
        bankCard.setStartDate(resultSet.getDate(BankCardSQL.START_DATE));
        bankCard.setEndDate(resultSet.getDate(BankCardSQL.END_DATE));
        bankCard.setRequest(resultSet.getBoolean(BankCardSQL.IS_REQUEST_SENT));
        bankCard.setBlock(resultSet.getBoolean(BankCardSQL.IS_BLOCKED));
        bankCard.setUserId(resultSet.getInt(BankCardSQL.USER));

        return bankCard;
    }
}
