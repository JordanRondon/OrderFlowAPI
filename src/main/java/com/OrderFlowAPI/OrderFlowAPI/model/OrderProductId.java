package com.OrderFlowAPI.OrderFlowAPI.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public record OrderProductId(Integer orderId, Integer productId) implements Serializable {
}