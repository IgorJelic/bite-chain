package com.bitechain.menuservice.dto;

import com.bitechain.menuservice.model.Item;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record WriteItemDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        BigDecimal price,
        String imageUrl,
        @DecimalMin(value = "0.00")
        @DecimalMax(value = "100.00")
        BigDecimal discount,
        boolean available,
        List<String> tags,
        UUID restaurantId
) {
  public Item getItem() {
    Item item = new Item();
    item.setName(name);
    item.setDescription(description);
    item.setPrice(price);
    item.setImageUrl(imageUrl);
    item.setDiscount(discount);
    item.setAvailable(available);
    item.setTags(tags);
    item.setRestaurantId(restaurantId);
    return item;
  }
}
