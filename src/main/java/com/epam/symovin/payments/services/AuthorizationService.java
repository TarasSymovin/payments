package com.epam.symovin.payments.services;

import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.User;

public class AuthorizationService {
    private static volatile AuthorizationService authorizationService;
    private DAOFactory mySqlDAO= DAOFactory.getDAOFactory();

    private AuthorizationService(){

    }

    public static AuthorizationService getInstance() {
        AuthorizationService localInstance = authorizationService;
        if (localInstance == null) {
            synchronized (AuthorizationService.class) {
                localInstance = authorizationService;
                if (localInstance == null) {
                    authorizationService = localInstance = new AuthorizationService();
                }
            }
        }
        return localInstance;
    }

    public User login(String login, String password){
        return mySqlDAO.getUserDAO().getUser(login, password);
    }
}
