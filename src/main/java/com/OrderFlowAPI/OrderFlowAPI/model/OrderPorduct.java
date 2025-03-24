package com.OrderFlowAPI.OrderFlowAPI.model;

public class OrderPorduct {
    private Product product;
    private int requestedAmount;

    public OrderPorduct() {
    }

    public OrderPorduct(Product product, int requestedAmount) {
        this.product = product;
        this.requestedAmount = requestedAmount;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRequestedAmount() {
        return this.requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
}
