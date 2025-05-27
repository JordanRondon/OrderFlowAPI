package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.exception.BusinessException;
import com.OrderFlowAPI.OrderFlowAPI.exception.ErrorCode;
import com.OrderFlowAPI.OrderFlowAPI.mapper.UserMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

@Service
public class UserService implements IUserService {

    private final IUserRepository iUserRepository;

    // injection by builder
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
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
