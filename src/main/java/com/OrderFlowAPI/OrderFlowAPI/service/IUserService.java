package com.OrderFlowAPI.OrderFlowAPI.service;

import java.util.List;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;

public interface IUserService {
    public UserDto getUserById(int userId);

    public List<UserDto> getAllUsers();
}
