package com.bitechain.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "users")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(unique = true)
  private String email;
  private String password;
  @Column(unique = true)
  private String activationToken;
  @Enumerated(EnumType.STRING)
  private Role role;
  private boolean active;
  Instant createdAt;
  Instant updatedAt;
}
