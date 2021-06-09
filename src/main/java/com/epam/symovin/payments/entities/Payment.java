package com.epam.symovin.payments.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private BigDecimal paymentSum;
    private String description;
    private Timestamp paymentDateTime;
    private int senderCardId;
    private int recipientCardId;
    private int paymentTypeId;
    private int paymentStateId;

    private BankCard recipientCard;
    private BankCard senderCard;
    private PaymentState paymentState;
    private PaymentType paymentType;

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Timestamp getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Timestamp paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public int getSenderCardId() {
        return senderCardId;
    }

    public void setSenderCardId(int senderCardId) {
        this.senderCardId = senderCardId;
    }

    public int getRecipientCardId() {
        return recipientCardId;
    }

    public void setRecipientCardId(int recipientCardId) {
        this.recipientCardId = recipientCardId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public int getPaymentStateId() {
        return paymentStateId;
    }

    public void setPaymentStateId(int paymentStateId) {
        this.paymentStateId = paymentStateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BankCard getRecipientCard() {
        return recipientCard;
    }

    public void setRecipientCard(BankCard recipientCard) {
        this.recipientCard = recipientCard;
    }

    public BankCard getSenderCard() {
        return senderCard;
    }

    public void setSenderCard(BankCard senderCard) {
        this.senderCard = senderCard;
    }
}
