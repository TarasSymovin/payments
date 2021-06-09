package com.epam.symovin.payments.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class BankCard {
    private int cardId;
    private String cardNumber;
    private String cardIban;
    private BigDecimal cardBalance;
    private BigDecimal creditLimitCurrent;
    private BigDecimal creditLimitAvailable;
    private boolean block;
    private Date startDate;
    private Date endDate;
    private int userId;
    private boolean request;

    private User user;
    private List<Payment> payments;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardIban() {
        return cardIban;
    }

    public void setCardIban(String cardIban) {
        this.cardIban = cardIban;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public BigDecimal getCreditLimitCurrent() {
        return creditLimitCurrent;
    }

    public void setCreditLimitCurrent(BigDecimal creditLimitCurrent) {
        this.creditLimitCurrent = creditLimitCurrent;
    }

    public BigDecimal getCreditLimitAvailable() {
        return creditLimitAvailable;
    }

    public void setCreditLimitAvailable(BigDecimal creditLimitAvailable) {
        this.creditLimitAvailable = creditLimitAvailable;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
