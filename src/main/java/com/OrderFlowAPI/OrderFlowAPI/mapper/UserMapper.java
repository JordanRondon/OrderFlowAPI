package com.OrderFlowAPI.OrderFlowAPI.mapper;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;
import com.OrderFlowAPI.OrderFlowAPI.model.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        RoleDto roleDto = RoleMapper.toDto(user.getRole());
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleDto(roleDto)
                .build();
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        Role role = RoleMapper.toEntity(userDto.getRoleDto());
        user.setRole(role);
        return user;
    }
}
