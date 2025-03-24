package com.OrderFlowAPI.OrderFlowAPI.model;

public class ProductStatus {
    private int statusId;
    private String name;

    public ProductStatus() {
    }

    public ProductStatus(int statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public int getStatusId() {
        return this.statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
