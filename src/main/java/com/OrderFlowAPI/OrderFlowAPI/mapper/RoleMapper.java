package com.OrderFlowAPI.OrderFlowAPI.mapper;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;

public class RoleMapper {
    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .roleId(role.getRoleId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setName(roleDto.getName());
        return role;
    }
}
