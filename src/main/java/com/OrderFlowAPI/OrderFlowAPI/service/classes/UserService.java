package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.LoginResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.mapper.RoleMapper;
import com.OrderFlowAPI.OrderFlowAPI.mapper.UserMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IRoleService;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;
import com.OrderFlowAPI.OrderFlowAPI.util.JwtUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final IUserRepository iUserRepository;
    private final IRoleService iRoleService;

    // injection by builder
    public UserService(PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
            IUserRepository iUserRepository, IRoleService iRoleService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("Email and password are required");
        }

        if (!iUserRepository.existsByEmail(loginRequestDto.getEmail())) {
            throw new RuntimeException("non-existent email");
        }

        User user = iUserRepository.findByEmail(loginRequestDto.getEmail());

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        UserDto userDto = UserMapper.toDto(user);
        return new LoginResponseDto(jwtUtil.generateJWT(userDto));
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));
        return UserMapper.toDto(user);
    }

}
