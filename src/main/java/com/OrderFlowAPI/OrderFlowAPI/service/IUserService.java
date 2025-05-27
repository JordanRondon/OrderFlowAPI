package com.OrderFlowAPI.OrderFlowAPI.service;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;

public interface IUserService {
    public UserDto getUserById(int userId);
}
