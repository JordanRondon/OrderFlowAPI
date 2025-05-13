package com.OrderFlowAPI.OrderFlowAPI.service;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.response.LoginResponseDto;

public interface IUserService {
    public void register(RegisterRequestDto registerRequestDto);

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public UserDto getUserById(int userId);
}
