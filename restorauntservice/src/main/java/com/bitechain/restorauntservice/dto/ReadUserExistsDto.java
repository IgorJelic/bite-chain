package com.bitechain.restorauntservice.dto;

import java.util.UUID;

public record ReadUserExistsDto(
  UUID userId,
  boolean exists
) {}
