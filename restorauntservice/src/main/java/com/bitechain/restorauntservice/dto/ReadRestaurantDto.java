package com.bitechain.restorauntservice.dto;

import com.bitechain.restorauntservice.model.Restaurant;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.UUID;

public record ReadRestaurantDto(
  UUID id,
  @NotBlank
  String name,
  @NotBlank
  String description,
  ReadAddressDto address,
  String website,
  String imageUrl,
  UUID ownerId
) {
  public ReadRestaurantDto(Restaurant restaurant) {
    this(
      restaurant.getId(),
      restaurant.getName(),
      restaurant.getDescription(),
      new ReadAddressDto(restaurant.getAddress()),
      Optional.ofNullable(restaurant.getWebsite()).orElse(""),
      Optional.ofNullable(restaurant.getImageUrl()).orElse(""),
      restaurant.getOwnerId()
    );
  }
}
