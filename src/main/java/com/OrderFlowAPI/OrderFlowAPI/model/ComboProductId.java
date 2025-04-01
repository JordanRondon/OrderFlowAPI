package com.OrderFlowAPI.OrderFlowAPI.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ComboProductId implements Serializable {
    private int comboId;
    private int productId;

    public ComboProductId() {
    }

    public ComboProductId(int comboId, int productId) {
        this.comboId = comboId;
        this.productId = productId;
    }

    public int getComboId() {
        return this.comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
