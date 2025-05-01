package com.OrderFlowAPI.OrderFlowAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService iUserService;

    // injection by builder
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
        try {
            UserDto userDto = iUserService.getUserById(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/ejemplo")
    private ResponseEntity<?> ejemplo(@RequestBody UserDto userDto) {
        try {
            System.out.println("id: " + userDto.getUserId());
            System.out.println("nombre: " + userDto.getFirstName());
            System.out.println("apellido: " + userDto.getLastName());
            System.out.println("email: " + userDto.getEmail());
            System.out.println("role: " + userDto.getRole().getRoleId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
