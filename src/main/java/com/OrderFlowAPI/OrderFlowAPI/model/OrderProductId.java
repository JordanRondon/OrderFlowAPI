package com.OrderFlowAPI.OrderFlowAPI.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public record OrderProductId(int orderId, int productId) implements Serializable {
}