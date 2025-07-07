package com.bitechain.userservice.dto;

import java.util.UUID;

public record ReadUserExistsDto(
        UUID userId,
        boolean exists
) {
}
