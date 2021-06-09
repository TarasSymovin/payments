package com.epam.symovin.payments.sql;

public class LocaleSQL {
    public static final String ID_LOCALE = "id_loc";
    public static final String LANG_CODE = "lang_code";

    public static final String GET_LOCALE_ID = "SELECT id_loc FROM locale WHERE lang_code = (?)";
}
