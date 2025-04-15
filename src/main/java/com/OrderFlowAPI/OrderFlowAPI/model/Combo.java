package com.OrderFlowAPI.OrderFlowAPI.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "combo")
public class Combo {
    @Id
    @Column(name = "combo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comboId;

    @Column(name = "combo_name", nullable = false, length = 30)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "number_of_requests", nullable = false)
    private int numberOfRequests = 0;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ProductStatus status;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComboProduct> products = new ArrayList<ComboProduct>();

    public Combo(String name, double price, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }
}
