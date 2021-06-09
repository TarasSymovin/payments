package com.epam.symovin.payments.entities;

public class PaymentState {
    private int paymentStateId;
    private String paymentState;

    public int getPaymentStateId() {
        return paymentStateId;
    }

    public void setPaymentStateId(int paymentStateId) {
        this.paymentStateId = paymentStateId;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }
}
