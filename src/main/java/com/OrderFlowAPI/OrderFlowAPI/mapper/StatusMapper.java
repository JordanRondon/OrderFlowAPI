package com.OrderFlowAPI.OrderFlowAPI.mapper;

import com.OrderFlowAPI.OrderFlowAPI.dto.StatusDto;
import com.OrderFlowAPI.OrderFlowAPI.model.OrderStatus;
import com.OrderFlowAPI.OrderFlowAPI.model.ProductStatus;
import com.OrderFlowAPI.OrderFlowAPI.model.UserStatus;

public class StatusMapper {
    public static StatusDto toDto(UserStatus userStatus) {
        return StatusDto.builder()
                .statusId(userStatus.getStatusId())
                .name(userStatus.getName())
                .build();
    }

    public static UserStatus toEntityUserStatus(StatusDto statusDto) {
        return new UserStatus(statusDto.getStatusId(), statusDto.getName());
    }

    public static StatusDto toDto(OrderStatus orderStatus) {
        return StatusDto.builder()
                .statusId(orderStatus.getStatusId())
                .name(orderStatus.getName())
                .build();
    }

    public static OrderStatus toEntityOrderStatus(StatusDto statusDto) {
        return new OrderStatus(statusDto.getStatusId(), statusDto.getName());
    }

    public static StatusDto toDto(ProductStatus productStatus) {
        return StatusDto.builder()
                .statusId(productStatus.getStatusId())
                .name(productStatus.getName())
                .build();
    }

    public static ProductStatus toEntityProductStatus(StatusDto statusDto) {
        return new ProductStatus(statusDto.getStatusId(), statusDto.getName());
    }

}
