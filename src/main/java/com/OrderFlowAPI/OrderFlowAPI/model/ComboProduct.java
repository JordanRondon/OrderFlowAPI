package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    public ComboProduct(Combo combo, Product product, int amount) {
        this.combo = combo;
        this.product = product;
        this.amount = amount;
    }
}
