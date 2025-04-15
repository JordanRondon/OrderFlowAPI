package com.OrderFlowAPI.OrderFlowAPI.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public record OrderComboId(int orderId, int comboId) implements Serializable {
}
