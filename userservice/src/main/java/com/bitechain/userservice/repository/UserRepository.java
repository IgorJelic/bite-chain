package com.bitechain.userservice.repository;

import com.bitechain.userservice.model.Role;
import com.bitechain.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  boolean existsByEmail(String email);
  User findByEmail(String email);
  User findByActivationToken(String activationToken);
  List<User> findAllByRole(Role role);
}
