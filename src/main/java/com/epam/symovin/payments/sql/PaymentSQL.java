package com.epam.symovin.payments.sql;

public class PaymentSQL {
    public static final String PAYMENT_ID = "payment_id";
    public static final String PAYMENT_SUM = "payment_sum";
    public static final String PAYMENT_DESCRIPTION = "description";
    public static final String PAYMENT_DATETIME = "payment_datetime";
    public static final String SENDER_CARD = "sender_card";
    public static final String RECIPIENT_CARD = "recipient_card";
    public static final String PAYMENT_TYPE = "payment_type";
    public static final String PAYMENT_STATE = "payment_state";

    public static final String SET_PAYMENT_STATE = "UPDATE payment SET payment_state = (?) WHERE payment_id = (?)";
    public static final String GET_PAYMENT_STATE = "SELECT * FROM payment_state WHERE payment_state_id = (?)";
    public static final String GET_PAYMENTS = "SELECT * FROM payment WHERE sender_card = (?) OR recipient_card = (?)";
    public static final String CREATE_PAYMENT = "INSERT INTO payment (payment_sum, description, sender_card, recipient_card) VALUES (?, ?, ?, ?)";
    public static final String GET_PAYMENT_BY_ID = "SELECT * FROM payment WHERE payment_id = (?)";

}
