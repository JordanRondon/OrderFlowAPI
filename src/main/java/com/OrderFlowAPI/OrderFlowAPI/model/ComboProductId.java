package com.OrderFlowAPI.OrderFlowAPI.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public record ComboProductId(Integer comboId, Integer productId) implements Serializable {
}
