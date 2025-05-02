package com.OrderFlowAPI.OrderFlowAPI.service;

import com.OrderFlowAPI.OrderFlowAPI.dto.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.LoginResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;

public interface IUserService {
    public void register(RegisterRequestDto registerRequestDto);

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public UserDto getUserById(int userId);
}
