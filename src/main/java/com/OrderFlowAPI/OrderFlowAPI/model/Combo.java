package com.OrderFlowAPI.OrderFlowAPI.model;

import java.util.List;

public class Combo {
    private int comboId;
    private ProductStatus status;
    private String name;
    private double price;
    private int numberOfRequests;
    private List<ComboProduct> products;

    public Combo() {
    }

    public Combo(int comboId, ProductStatus status, String name, double price,
            int numberOfRequests, List<ComboProduct> products) {
        this.comboId = comboId;
        this.status = status;
        this.name = name;
        this.price = price;
        this.numberOfRequests = numberOfRequests;
        this.products = products;
    }

    public int getComboId() {
        return this.comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
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

    public List<ComboProduct> getProducts() {
        return this.products;
    }

    public void setProducts(List<ComboProduct> products) {
        this.products = products;
    }

    public void addProduct(ComboProduct comboProduct) {
        this.products.add(comboProduct);
    }

    public void removeProduct(int productId) {
        this.products.remove(productId);
    }
}
