package com.OrderFlowAPI.OrderFlowAPI.mapper;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;
import com.OrderFlowAPI.OrderFlowAPI.model.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        RoleDto roleDto = RoleMapper.toDto(user.getRole());
        userDto.setRoleDto(roleDto);
        return userDto;
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
