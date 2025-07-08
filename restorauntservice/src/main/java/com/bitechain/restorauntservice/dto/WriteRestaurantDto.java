package com.bitechain.restorauntservice.dto;

import com.bitechain.restorauntservice.model.Restaurant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record WriteRestaurantDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @Valid
        WriteAddressDto address,
        String website,
        String imageUrl,
        @NotBlank
        UUID ownerId
) {
  public Restaurant getRestaurant() {
    Restaurant restaurant = new Restaurant();
    restaurant.setName(name);
    restaurant.setDescription(description);
    restaurant.setAddress(address.getAddress());
    restaurant.setWebsite(website);
    restaurant.setImageUrl(imageUrl);
    restaurant.setOwnerId(ownerId);
    return restaurant;
  }
}
