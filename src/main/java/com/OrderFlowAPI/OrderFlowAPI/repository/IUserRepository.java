package com.OrderFlowAPI.OrderFlowAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.OrderFlowAPI.OrderFlowAPI.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.status WHERE u.email = :email AND u.status.statusId = 1")
    public Optional<User> findActiveByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u ORDER BY u.userId ASC")
    public List<User> findAll();
}
