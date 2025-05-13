package com.OrderFlowAPI.OrderFlowAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Validation errors
    VALIDATION_ERROR("VALID-001", "Validation error"), // error validation in DTO
    INVALID_INPUT("VALID-002", "Invalid input"), // error validation in URL

    // Authentication errors
    AUTH_EMAIL_NOT_FOUND("AUTH-001", "Email not found"),
    AUTH_PASSWORD_MISMATCH("AUTH-002", "Password mismatch"),
    AUTH_EMAIL_ALREADY_EXISTS("AUTH-003", "The email is already registered"),

    // User errors
    USER_NOT_FOUND("USER-001", "User not found"),
    USER_INACTIVE("USER-002", "Inactive user"),

    // Role errors
    ROLE_NOT_FOUND("ROLE-001", "Role nor found");

    private final String code;
    private final String defaultMessage;
}
