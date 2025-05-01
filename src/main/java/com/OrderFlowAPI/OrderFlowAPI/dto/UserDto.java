package com.OrderFlowAPI.OrderFlowAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDto roleDto;
}
