package com.bitechain.userservice.dto;

import com.bitechain.userservice.model.Role;
import com.bitechain.userservice.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record WriteUserDto(
        @Email
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters long")
        String password
) {
  public User getCustomer() {
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(Role.CUSTOMER);
    user.setActive(false); // Default to unactive
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    return user;
  }

  public User getOwner() {
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(Role.OWNER);
    user.setActive(false); // Default to unactive
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    return user;
  }

  public User getAdmin() {
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(Role.ADMIN);
    user.setActive(true); // Default to active
    user.setCreatedAt(Instant.now());
    user.setUpdatedAt(Instant.now());
    return user;
  }
}
