package com.OrderFlowAPI.OrderFlowAPI.mapper;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.StatusDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.model.UserStatus;

public class UserMapper {
    public static UserDto toDto(User user) {
        RoleDto roleDto = RoleMapper.toDto(user.getRole());
        StatusDto statusDto = StatusMapper.toDto(user.getStatus());
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleDto(roleDto)
                .statusDto(statusDto)
                .build();
    }

    public static User toEntity(UserDto userDto) {
        Role role = RoleMapper.toEntity(userDto.getRoleDto());
        UserStatus userStatus = StatusMapper.toEntityUserStatus(userDto.getStatusDto());
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(role);
        user.setStatus(userStatus);
        return user;
    }
}
