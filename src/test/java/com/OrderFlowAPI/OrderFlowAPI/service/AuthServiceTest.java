package com.OrderFlowAPI.OrderFlowAPI.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.OrderFlowAPI.OrderFlowAPI.dto.RoleDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.LoginRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.request.RegisterRequestDto;
import com.OrderFlowAPI.OrderFlowAPI.dto.response.LoginResponseDto;
import com.OrderFlowAPI.OrderFlowAPI.exception.BusinessException;
import com.OrderFlowAPI.OrderFlowAPI.exception.ErrorCode;
import com.OrderFlowAPI.OrderFlowAPI.model.Role;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.model.UserStatus;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.classes.AuthService;
import com.OrderFlowAPI.OrderFlowAPI.service.classes.RoleService;
import com.OrderFlowAPI.OrderFlowAPI.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private IUserRepository iUserRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    private RegisterRequestDto registerRequestDto;
    private RoleDto roleDto;

    private LoginRequestDto loginRequestDto;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        registerRequestDto = RegisterRequestDto.builder()
                .firstName("Juan Hector")
                .lastName("Doest Smeit")
                .email("juan.doe@gmail.com")
                .password("password123")
                .roleId(1)
                .build();

        roleDto = RoleDto.builder()
                .roleId(1)
                .name("admin")
                .build();

        loginRequestDto = new LoginRequestDto("juan.doe@gmail.com", "password123");

        role = new Role(1, "admin");

        user = new User(
                1,
                "Juan Hector",
                "Doest Smeit",
                "juan.doe@gmail.com",
                "encodedPassword",
                role,
                new UserStatus(1, "enabled"));
    }

    @Test
    @DisplayName("registration test with new email")
    public void whenRegisterWithNewEmail_thenSuccess() {
        // Given
        when(iUserRepository.existsByEmail("juan.doe@gmail.com")).thenReturn(false);
        when(roleService.findById(1)).thenReturn(roleDto);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // When
        this.authService.register(registerRequestDto);

        // Then
        verify(iUserRepository, times(1)).existsByEmail("juan.doe@gmail.com");
        verify(roleService, times(1)).findById(1);
        verify(passwordEncoder, times(1)).encode("password123");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(iUserRepository, times(1)).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertAll("Saved User Validation",
                () -> assertEquals("Juan Hector", savedUser.getFirstName()),
                () -> assertEquals("Doest Smeit", savedUser.getLastName()),
                () -> assertEquals("juan.doe@gmail.com", savedUser.getEmail()),
                () -> assertEquals("encodedPassword", savedUser.getPassword()),
                () -> {
                    Role role = savedUser.getRole();
                    assertAll("Role validation",
                            () -> assertEquals(1, role.getRoleId()),
                            () -> assertEquals("admin", role.getName()));
                });
    }

    @Test
    @DisplayName("registration test with email existing")
    public void whenRegisterWithExistingEmail_thenThrowBusinessException() {
        // Given
        when(iUserRepository.existsByEmail("juan.doe@gmail.com")).thenReturn(true);

        // When
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authService.register(registerRequestDto);
        });

        // Then
        assertAll("Exception Validation",
                () -> assertEquals("Existing email", exception.getMessage()),
                () -> assertEquals(ErrorCode.AUTH_EMAIL_ALREADY_EXISTS, exception.getErrorCode()),
                () -> assertEquals(HttpStatus.CONFLICT, exception.getStatus()));

        verify(iUserRepository, times(1)).existsByEmail(registerRequestDto.getEmail());
        verify(iUserRepository, never()).save(any(User.class));
    }

    @Test
    public void loginWithValidCredentials_returnsToken() {
        // Given
        when(iUserRepository.findActiveByEmail("juan.doe@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", user.getPassword())).thenReturn(true);
        when(jwtUtil.generateJWT(any(UserDto.class))).thenReturn("mock.token");

        // When
        LoginResponseDto response = authService.login(loginRequestDto);

        // Then
        verify(iUserRepository, times(1)).findActiveByEmail("juan.doe@gmail.com");
        verify(passwordEncoder, times(1)).matches("password123", user.getPassword());
        ArgumentCaptor<UserDto> userDtoCaptor = ArgumentCaptor.forClass(UserDto.class);
        verify(jwtUtil, times(1)).generateJWT(userDtoCaptor.capture());
        UserDto userDto = userDtoCaptor.getValue();
        assertAll("userDto validation",
                () -> assertEquals("Juan Hector", userDto.getFirstName()),
                () -> assertEquals("Doest Smeit", userDto.getLastName()),
                () -> assertEquals("juan.doe@gmail.com", userDto.getEmail()),
                () -> {
                    RoleDto roleDto = userDto.getRoleDto();
                    assertAll("Role validation",
                            () -> assertEquals(1, roleDto.getRoleId()),
                            () -> assertEquals("admin", roleDto.getName()));
                });
        assertNotNull(response.getToken());
    }

    @Test
    public void loginWithInvalidEmail_throwsException() {
        // Given
        when(iUserRepository.findActiveByEmail(anyString())).thenReturn(Optional.empty());

        // When
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authService.login(new LoginRequestDto("invalid@email.com", "any"));
        });

        // then
        verify(iUserRepository, times(1)).findActiveByEmail(anyString());
        assertAll("Exception Validation",
                () -> assertEquals("Email not registered or disabled: invalid@email.com", exception.getMessage()),
                () -> assertEquals(ErrorCode.AUTH_EMAIL_NOT_FOUND, exception.getErrorCode()),
                () -> assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus()));
    }

    @Test
    public void loginWithInvalidPassword_throwsException() {
        // Given
        when(iUserRepository.findActiveByEmail("juan.doe@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // When
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authService.login(new LoginRequestDto("juan.doe@gmail.com", "any"));
        });

        // Then
        verify(iUserRepository, times(1)).findActiveByEmail("juan.doe@gmail.com");
        assertAll("Exception Validation",
                () -> assertEquals("Incorrect password", exception.getMessage()),
                () -> assertEquals(ErrorCode.AUTH_PASSWORD_MISMATCH, exception.getErrorCode()),
                () -> assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus()));
    }
}
