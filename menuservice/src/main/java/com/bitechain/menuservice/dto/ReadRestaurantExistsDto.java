package com.bitechain.menuservice.dto;

import java.util.UUID;

public record ReadRestaurantExistsDto(
        UUID restaurantId,
        boolean exists
) {
}
