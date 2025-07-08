package com.bitechain.menuservice.dto;

import com.bitechain.menuservice.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ReadItemDto(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        BigDecimal discount,
        boolean available,
        List<String> tags,
        UUID restaurantId
) {
  public ReadItemDto(Item menuItem) {
    this(
            menuItem.getId(),
            menuItem.getName(),
            menuItem.getDescription(),
            menuItem.getPrice(),
            menuItem.getImageUrl(),
            menuItem.getDiscount(),
            menuItem.isAvailable(),
            menuItem.getTags(),
            menuItem.getRestaurantId()
    );
  }
}
