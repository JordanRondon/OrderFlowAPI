package com.OrderFlowAPI.OrderFlowAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderFlowAPI.OrderFlowAPI.model.User;
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
    private ResponseEntity<User> getUserById(@PathVariable int userId) {
        try {
            User user = iUserService.getUserById(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
