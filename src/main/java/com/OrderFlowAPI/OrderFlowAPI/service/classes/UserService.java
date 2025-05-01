package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.mapper.RoleMapper;
import com.OrderFlowAPI.OrderFlowAPI.mapper.UserMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IRoleService;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository iUserRepository;
    private final IRoleService iRoleService;

    // injection by builder
    public UserService(PasswordEncoder passwordEncoder, IUserRepository iUserRepository,
            IRoleService iRoleService) {
        this.passwordEncoder = passwordEncoder;
        this.iUserRepository = iUserRepository;
        this.iRoleService = iRoleService;
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        if (registerRequestDto.getEmail() == null || registerRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("Email and password are required");
        }

        if (iUserRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new RuntimeException("Existing email");
        }

        RoleDto roleDto = iRoleService.findById(registerRequestDto.getRoleId());

        User user = new User();
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(RoleMapper.toEntity(roleDto));

        iUserRepository.save(user);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));
        return UserMapper.toDto(user);
    }

}
