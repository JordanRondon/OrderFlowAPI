package com.OrderFlowAPI.OrderFlowAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderFlowAPI.OrderFlowAPI.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
