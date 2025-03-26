package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name", nullable = false, length = 30)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "number_of_requests", nullable = false)
    private int numberOfRequests;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ProductStatus status;

    public Product() {
    }

    public Product(int productId, Category category, ProductStatus status, String name, double price) {
        this.productId = productId;
        this.category = category;
        this.status = status;
        this.name = name;
        this.price = price;
        this.numberOfRequests = 0;
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
