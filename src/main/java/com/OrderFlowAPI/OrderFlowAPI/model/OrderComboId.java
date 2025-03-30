package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderComboId {
    private int orderId;
    private int comboId;

    public OrderComboId() {
    }

    public OrderComboId(int orderId, int comboId) {
        this.orderId = orderId;
        this.comboId = comboId;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getComboId() {
        return this.comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }
}
