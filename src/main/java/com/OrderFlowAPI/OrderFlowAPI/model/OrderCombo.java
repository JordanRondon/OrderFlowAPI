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
@Table(name = "orders_combo")
public class OrderCombo {
    @EmbeddedId
    private OrderComboId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne
    @MapsId("comboId")
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @Column(name = "requested_amount")
    private int requestedAmount;

    public OrderCombo(Order order, Combo combo, int requestedAmount) {
        this.order = order;
        this.combo = combo;
        this.requestedAmount = requestedAmount;
    }
}
