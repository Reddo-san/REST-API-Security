package com.rest_api.rest_api_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.rest_api_demo.entity.Auth_Role;

public interface AuthRoleRepository extends JpaRepository<Auth_Role,Long> {
  Optional<Auth_Role> findByName(String theName);
  Boolean existsByName(String theUsername);

}
