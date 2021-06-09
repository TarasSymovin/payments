package com.epam.symovin.payments.entities;

public class Locale {
    private int idLocale;
    private String langCode;

    public int getIdLocale() {
        return idLocale;
    }

    public void setIdLocale(int idLocale) {
        this.idLocale = idLocale;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
}
