package com.OrderFlowAPI.OrderFlowAPI.service;

import com.OrderFlowAPI.OrderFlowAPI.dto.request.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.response.LoginResponseDto;

public interface IAuthService {
    public void register(RegisterRequestDto registerRequestDto);

    public LoginResponseDto login(LoginRequestDto loginRequestDto);
}
