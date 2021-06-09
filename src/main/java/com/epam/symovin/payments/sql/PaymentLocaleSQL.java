package com.epam.symovin.payments.sql;

public class PaymentLocaleSQL {
    public static final String PAYMENT_TYPE_ID = "payment_type_id";
    public static final String LOCALE_ID = "locale_id_loc";
    public static final String PAYMENT_TYPE_NAME = "payment_type";

    public static final String GET_PAYMENT_NAME = "SELECT payment_type FROM payment_type_locale WHERE payment_type_id = (?) AND locale_id_loc = (?)";
}
