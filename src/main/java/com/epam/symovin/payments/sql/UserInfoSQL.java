package com.epam.symovin.payments.sql;

public class UserInfoSQL {
    public static final String USER_INFO_ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String INCOME = "income";
    public static final String TAX_PAYER_NUMBER = "tax_payer_number";

    public static final String GET_USER_INFO_BY_ID = "SELECT * FROM user_info WHERE id = (?)";
    public static final String ADD_USER_INFO = "INSERT INTO user_info " +
            "(first_name, last_name, income, tax_payer_number) VALUES (?, ?, ?, ?)";

}
