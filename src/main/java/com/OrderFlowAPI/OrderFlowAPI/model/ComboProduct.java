package com.OrderFlowAPI.OrderFlowAPI.model;

public class ComboProduct {
    private Product product;
    private int amount;

    public ComboProduct() {
    }

    public ComboProduct(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
