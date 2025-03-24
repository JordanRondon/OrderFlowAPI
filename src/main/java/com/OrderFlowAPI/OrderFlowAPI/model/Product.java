package com.OrderFlowAPI.OrderFlowAPI.model;

public class Product {
    private int productId;
    private Category category;
    private ProductStatus status;
    private String name;
    private double price;
    private int numberOfRequests;

    public Product() {
    }

    public Product(int productId, Category category, ProductStatus status, String name, double price,
            int numberOfRequests) {
        this.productId = productId;
        this.category = category;
        this.status = status;
        this.name = name;
        this.price = price;
        this.numberOfRequests = numberOfRequests;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductStatus getProductStatus() {
        return this.status;
    }

    public void setProductStatus(ProductStatus status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfRequests() {
        return this.numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }
}
