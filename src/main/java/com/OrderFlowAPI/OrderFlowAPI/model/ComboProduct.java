package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "combo_product")
public class ComboProduct {
    @EmbeddedId
    private ComboProductId id;

    @ManyToOne
    @MapsId("comboId")
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private int amount;

    public ComboProduct() {
    }

    public ComboProduct(Combo combo, Product product, int amount) {
        this.combo = combo;
        this.product = product;
        this.amount = amount;
    }

    public Combo getCombo() {
        return this.combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
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
