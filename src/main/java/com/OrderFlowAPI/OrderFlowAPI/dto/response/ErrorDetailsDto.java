package com.OrderFlowAPI.OrderFlowAPI.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetailsDto {
    private LocalDateTime timestamp;
    private String code;
    private String message;
    private String details;
}
