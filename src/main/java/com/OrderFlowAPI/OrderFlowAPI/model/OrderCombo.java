package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class OrderCombo {
    @EmbeddedId
    private OrderComboId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("comboId")
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @Column(name = "requested_amount")
    private int requestedAmount;

    public OrderCombo() {
    }

    public OrderCombo(Combo combo, int requestedAmount) {
        this.combo = combo;
        this.requestedAmount = requestedAmount;
    }

    public Combo getCombo() {
        return this.combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public int getRequestedAmount() {
        return this.requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
}
