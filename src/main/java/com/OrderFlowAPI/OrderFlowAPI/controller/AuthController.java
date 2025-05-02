package com.OrderFlowAPI.OrderFlowAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderFlowAPI.OrderFlowAPI.dto.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.LoginResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    public final IUserService iUserService;

    // injection by builder
    public AuthController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
        try {
            iUserService.register(registerRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            LoginResponseDto token = iUserService.login(loginRequestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server error");
        }
    }
}
