package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractProduct {
    @Column(name = "item_name", nullable = false, length = 30)
    protected String name;

    @Column(name = "price", nullable = false)
    protected double price;

    @Column(name = "number_of_requests", nullable = false)
    protected int numberOfRequests = 0;

    @ManyToOne
    @JoinColumn(name = "status_id")
    protected ProductStatus status;

    protected AbstractProduct(String name, double price, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }
}
