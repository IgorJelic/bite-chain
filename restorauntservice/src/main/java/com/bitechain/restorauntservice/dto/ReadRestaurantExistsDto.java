package com.bitechain.restorauntservice.dto;

import java.util.UUID;

public record ReadRestaurantExistsDto(
        UUID restaurantId,
        boolean exists
) {
}
