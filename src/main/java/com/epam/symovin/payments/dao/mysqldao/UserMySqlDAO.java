package com.epam.symovin.payments.dao.mysqldao;

import com.epam.symovin.payments.dao.factory.Connector;
import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.dao.interfaces.UserDAO;
import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.User;
import com.epam.symovin.payments.entities.UserInfo;
import com.epam.symovin.payments.entities.UserType;
import com.epam.symovin.payments.sql.UserInfoSQL;
import com.epam.symovin.payments.sql.UserSQL;
import com.epam.symovin.payments.sql.UserTypeSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMySqlDAO implements UserDAO {

    private static volatile UserMySqlDAO userMySqlDAO;

    public static UserMySqlDAO getInstance() {
        UserMySqlDAO localInstance = userMySqlDAO;
        if (localInstance == null) {
            synchronized (UserMySqlDAO.class) {
                localInstance = userMySqlDAO;
                if (localInstance == null) {
                    userMySqlDAO = localInstance = new UserMySqlDAO();
                }
            }
        }
        return localInstance;
    }

    private UserMySqlDAO() {
    }

    @Override
    public User getUser(String login, String pass) {
        User user = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_USER_BY_LOGIN_AND_PASS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapUser(resultSet);
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setBankCards(DAOFactory.getDAOFactory().getBankCardDAO().getBankCardsForUser(user));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUser(int id) {
        User user = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = mapUser(resultSet);
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setBankCards(DAOFactory.getDAOFactory().getBankCardDAO().getBankCardsForUser(user));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = new UserInfo();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserInfoSQL.GET_USER_INFO_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userInfo = mapUserInfo(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userInfo;
    }

    @Override
    public UserType getUserType(int id) {
        UserType userType = new UserType();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserTypeSQL.GET_USER_TYPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userType = mapUserType(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userType;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.GET_ALL_USERS)) {
            preparedStatement.setInt(1, 2);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = mapUser(resultSet);
                    user.setUserInfo(getUserInfo(user.getUserInfoId()));
                    user.setUserType(getUserType(user.getUserTypeId()));
                    user.setBankCards(DAOFactory.getDAOFactory().getBankCardDAO().getBankCardsForUser(user));
                    userList.add(user);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UserSQL.ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isActive());
            preparedStatement.setInt(4, user.getUserType().getTypeId());
            preparedStatement.setObject(5, addUserInfo(user.getUserInfo()));

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        user.setUserId(resultSet.getInt(1));
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
    public boolean setActiveForUser(User user, boolean state) {
        boolean result = false;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.BLOCK_USER)) {
            preparedStatement.setBoolean(1, state);
            preparedStatement.setInt(2, user.getUserId());

            if (preparedStatement.executeUpdate() > 0){
                user.setActive(state);
                result = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public Integer addUserInfo(UserInfo userInfo) {
        Integer result = null;

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UserInfoSQL.ADD_USER_INFO, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, userInfo.getFirstName());
            preparedStatement.setString(2, userInfo.getLastName());
            preparedStatement.setBigDecimal(3, userInfo.getIncome());
            preparedStatement.setString(4, userInfo.getTaxPayerNumber());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        userInfo.setId(resultSet.getInt(1));
                        result = userInfo.getId();
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }


    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setUserId(resultSet.getInt(UserSQL.ID));
        user.setLogin(resultSet.getString(UserSQL.LOGIN));
        user.setPassword(resultSet.getString(UserSQL.PASSWORD));
        user.setUserTypeId(resultSet.getInt(UserSQL.USER_TYPE));
        user.setActive(resultSet.getBoolean(UserSQL.IS_ACTIVE));
        user.setUserInfoId(resultSet.getInt(UserSQL.USER_INFO));

        return user;
    }

    private UserInfo mapUserInfo(ResultSet resultSet) throws SQLException {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(resultSet.getInt(UserInfoSQL.USER_INFO_ID));
        userInfo.setFirstName(resultSet.getString(UserInfoSQL.FIRST_NAME));
        userInfo.setLastName(resultSet.getString(UserInfoSQL.LAST_NAME));
        userInfo.setIncome(resultSet.getBigDecimal(UserInfoSQL.INCOME));
        userInfo.setTaxPayerNumber(resultSet.getString(UserInfoSQL.TAX_PAYER_NUMBER));

        return userInfo;
    }

    private UserType mapUserType(ResultSet resultSet) throws SQLException {
        UserType userType = new UserType();

        userType.setTypeId(resultSet.getInt(UserTypeSQL.USER_TYPE_ID));
        userType.setUserType(resultSet.getString(UserTypeSQL.USER_TYPE));

        return userType;
    }

}
