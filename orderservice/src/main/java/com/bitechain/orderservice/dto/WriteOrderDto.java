package com.bitechain.orderservice.dto;

import java.util.List;
import java.util.UUID;

public record WriteOrderDto(
        UUID userId,
        UUID restaurantId,
        List<WriteOrderItemDto> items
) {}
