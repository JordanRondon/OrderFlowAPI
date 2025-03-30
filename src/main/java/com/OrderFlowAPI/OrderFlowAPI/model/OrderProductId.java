package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderProductId {
    private int orderId;
    private int productId;

    public OrderProductId() {
    }

    public OrderProductId(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
