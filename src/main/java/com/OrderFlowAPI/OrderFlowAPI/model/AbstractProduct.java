package com.OrderFlowAPI.OrderFlowAPI.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractProduct {
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "number_of_requests", nullable = false)
    private int numberOfRequests = 0;

    @ManyToAny
    @JoinColumn(name = "status_id")
    private ProductStatus status;

    public AbstractProduct(String name, double price, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }
}
