package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;
import com.OrderFlowAPI.OrderFlowAPI.mapper.UserMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.User;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserService;

import jakarta.persistence.EntityNotFoundException;

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
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));
        return UserMapper.toDto(user);
    }

}
