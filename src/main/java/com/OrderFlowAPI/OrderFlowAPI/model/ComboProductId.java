package com.OrderFlowAPI.OrderFlowAPI.model;

import java.io.Serializable;

public record ComboProductId(int comboId, int productId) implements Serializable {
}
