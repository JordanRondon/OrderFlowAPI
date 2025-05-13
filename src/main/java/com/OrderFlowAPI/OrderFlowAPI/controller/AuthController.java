package com.OrderFlowAPI.OrderFlowAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderFlowAPI.OrderFlowAPI.dto.request.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.response.GenericResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.response.LoginResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    public final IUserService iUserService;

    // injection by builder
    public AuthController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Operation(summary = "Register", security = @SecurityRequirement(name = "none"))
    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        iUserService.register(registerRequestDto);
        return ResponseEntity.ok(new GenericResponseDto("Register successful"));
    }

    @Operation(summary = "Login", security = @SecurityRequirement(name = "none"))
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto token = iUserService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
