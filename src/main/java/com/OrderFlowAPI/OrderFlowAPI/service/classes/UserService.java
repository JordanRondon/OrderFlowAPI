package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.response.LoginResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.exception.BusinessException;
import com.OrderFlowAPI.OrderFlowAPI.exception.ErrorCode;
import com.OrderFlowAPI.OrderFlowAPI.mapper.RoleMapper;
import com.OrderFlowAPI.OrderFlowAPI.mapper.UserMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IRoleService;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;
import com.OrderFlowAPI.OrderFlowAPI.util.JwtUtil;

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
        if (iUserRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new BusinessException("Existing email",
                    ErrorCode.AUTH_EMAIL_ALREADY_EXISTS,
                    HttpStatus.CONFLICT);
        }

        RoleDto roleDto = iRoleService.findById(registerRequestDto.getRoleId());

        User user = new User(
                registerRequestDto.getFirstName(),
                registerRequestDto.getLastName(),
                registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()),
                RoleMapper.toEntity(roleDto));

        iUserRepository.save(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = iUserRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new BusinessException("unregistered email: " + loginRequestDto.getEmail(),
                        ErrorCode.AUTH_EMAIL_NOT_FOUND,
                        HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new BusinessException("Incorrect password",
                    ErrorCode.AUTH_PASSWORD_MISMATCH,
                    HttpStatus.UNAUTHORIZED);
        }

        UserDto userDto = UserMapper.toDto(user);
        return new LoginResponseDto(jwtUtil.generateJWT(userDto));
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found with ID: " + userId,
                        ErrorCode.USER_NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        return UserMapper.toDto(user);
    }

}
