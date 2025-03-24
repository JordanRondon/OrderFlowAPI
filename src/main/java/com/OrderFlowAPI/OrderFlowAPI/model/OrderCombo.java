package com.OrderFlowAPI.OrderFlowAPI.model;

public class OrderCombo {
    private Combo combo;
    private int requestedAmount;

    public OrderCombo() {
    }

    public OrderCombo(Combo combo, int requestedAmount) {
        this.combo = combo;
        this.requestedAmount = requestedAmount;
    }

    public Combo getCombo() {
        return this.combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public int getRequestedAmount() {
        return this.requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
}
