package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.exception.BusinessException;
import com.OrderFlowAPI.OrderFlowAPI.exception.ErrorCode;
import com.OrderFlowAPI.OrderFlowAPI.mapper.RoleMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;
import com.OrderFlowAPI.OrderFlowAPI.repository.IRoleRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IRoleService;

@Service
public class RoleService implements IRoleService {

    public final IRoleRepository iRoleRepository;

    public RoleService(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public RoleDto findById(int roleId) {
        Role role = iRoleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessException("Role not found with id: " + roleId,
                        ErrorCode.ROLE_NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        return RoleMapper.toDto(role);
    }

}
