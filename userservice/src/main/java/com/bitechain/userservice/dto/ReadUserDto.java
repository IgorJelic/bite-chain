package com.bitechain.userservice.dto;

import com.bitechain.userservice.model.User;

import java.time.Instant;
import java.util.UUID;

public record ReadUserDto(
        UUID id,
        String email,
        String role,
        String activationToken,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
  public ReadUserDto(User user) {
    this(
            user.getId(),
            user.getEmail(),
            user.getRole().name(),
            user.getActivationToken(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
    );
  }
}
