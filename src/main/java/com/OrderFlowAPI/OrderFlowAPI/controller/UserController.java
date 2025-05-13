package com.OrderFlowAPI.OrderFlowAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;

@Validated // This enables validation of @PathVariable and @RequestParam
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService iUserService;

    // injection by builder
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Operation(summary = "Get user by id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable @Min(value = 1, message = "User id must be greater than or equal to 1") int userId) {
        UserDto userDto = iUserService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

}
