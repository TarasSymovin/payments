package com.epam.symovin.payments.services;


import com.epam.symovin.payments.dao.factory.DAOFactory;
import com.epam.symovin.payments.entities.BankCard;
import com.epam.symovin.payments.entities.Payment;
import com.epam.symovin.payments.entities.User;
import org.iban4j.Iban;

import java.util.Random;

public class AddingService {
    private DAOFactory mySqlDAO = DAOFactory.getDAOFactory();

    private static volatile AddingService addingService;

    private AddingService() {

    }

    public static AddingService getInstance() {
        AddingService localInstance = addingService;
        if (localInstance == null) {
            synchronized (AddingService.class) {
                localInstance = addingService;
                if (localInstance == null) {
                    addingService = localInstance = new AddingService();
                }
            }
        }
        return localInstance;
    }

    public boolean addBankCardForUser(User user){
        BankCard bankCard = new BankCard();
        bankCard.setCardNumber(generate(16));
        bankCard.setCardIban(Iban.random().toString());
        bankCard.setUserId(user.getUserId());

        return DAOFactory.getDAOFactory().getBankCardDAO().addBankCard(bankCard);
    }

    public String generate(int length) {
        Random random = new Random(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }

        return builder.toString();
    }
}
