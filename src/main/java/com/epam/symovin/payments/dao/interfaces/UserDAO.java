package com.epam.symovin.payments.dao.interfaces;

import com.epam.symovin.payments.entities.User;
import com.epam.symovin.payments.entities.UserInfo;
import com.epam.symovin.payments.entities.UserType;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUser(String login, String pass);

    User getUser(int id);

    UserInfo getUserInfo(int id);

    UserType getUserType(int id);

    boolean addUser(User user);

    boolean setActiveForUser(User user, boolean state);

    Integer addUserInfo(UserInfo userInfo);
}
