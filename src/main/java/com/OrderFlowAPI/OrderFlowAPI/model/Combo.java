package com.OrderFlowAPI.OrderFlowAPI.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "combo")
public class Combo extends AbstractProduct {
    @Id
    @Column(name = "combo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comboId;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComboProduct> products = new ArrayList<ComboProduct>();

    public Combo() {
        super();
    }

    public Combo(String name, double price, ProductStatus status) {
        super(name, price, status);
    }
}
