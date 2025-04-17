package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.stereotype.Service;

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
    public User getUserById(int userId) {
        return iUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));
    }

}
