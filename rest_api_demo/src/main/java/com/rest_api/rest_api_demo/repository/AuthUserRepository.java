package com.rest_api.rest_api_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.rest_api_demo.entity.Auth_User;

public interface AuthUserRepository extends JpaRepository<Auth_User,Long> {
  Optional<Auth_User> findByUsername(String theUsername);
  Boolean existsByUsername(String theUsername);

}
