package com.epam.symovin.payments.sql;

public class BankCardSQL {
    public static final String CARD_ID = "card_id";
    public static final String CARD_NUMBER = "card_number";
    public static final String CARD_IBAN = "card_iban";
    public static final String CARD_BALANCE = "card_balance";
    public static final String CREDIT_LIMIT_CURRENT = "credit_limit_current";
    public static final String CREDIT_LIMIT_AVAILABLE = "credit_limit_available";
    public static final String IS_BLOCKED = "is_blocked";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String USER = "user";
    public static final String IS_REQUEST_SENT = "is_request_sent";

    public static final String SUBTRACT_BALANCE = "UPDATE bank_card SET card_balance = card_balance - (?) WHERE card_number = (?)";
    public static final String ADD_BALANCE = "UPDATE bank_card SET card_balance = card_balance + (?) WHERE card_number = (?)";
    public static final String GET_BANKCARD_FOR_USER = "SELECT * FROM bank_card WHERE user = (?)";
    public static final String GET_BANKCARD = "SELECT * FROM bank_card WHERE card_id = (?)";
    public static final String GET_BANKCARD_BY_NUMBER = "SELECT * FROM bank_card WHERE card_number = (?)";
    public static final String ADD_BANKCARD = "INSERT INTO bank_card " +
            "(card_number, card_iban, user) VALUES (?, ?, ?)";
    public static final String BLOCK_CARD = "UPDATE bank_card SET is_blocked = (?) WHERE card_id = (?)";
    public static final String SET_REQUEST_STATUS = "UPDATE bank_card SET is_request_sent = (?) WHERE card_id = (?)";
    public static final String GET_REQUESTS = "SELECT * FROM bank_card WHERE is_request_sent = (?)";
}
