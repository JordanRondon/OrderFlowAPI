package com.OrderFlowAPI.OrderFlowAPI.service;

import com.OrderFlowAPI.OrderFlowAPI.dto.StatusDto;

public interface IUserStatusService {
    public StatusDto findById(int statusId);
}
