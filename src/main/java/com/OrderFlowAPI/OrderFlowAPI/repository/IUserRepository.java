package com.OrderFlowAPI.OrderFlowAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderFlowAPI.OrderFlowAPI.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String email);

    public Optional<User> findByEmail(String email);
}
