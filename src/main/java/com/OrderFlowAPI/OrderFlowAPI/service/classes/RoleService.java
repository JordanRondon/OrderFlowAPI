package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.mapper.RoleMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;
import com.OrderFlowAPI.OrderFlowAPI.repository.IRoleRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IRoleService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService implements IRoleService {

    public final IRoleRepository iRoleRepository;

    public RoleService(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public RoleDto findById(int roleId) {
        Role role = iRoleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
        return RoleMapper.toDto(role);
    }

}
