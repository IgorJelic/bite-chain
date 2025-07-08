package com.bitechain.menuservice.client;

import com.bitechain.menuservice.dto.ReadRestaurantExistsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "restaurant-service", url = "${restaurant.service.url}")
public interface RestaurantClient {
  @GetMapping("/api/v1/restaurants/exists/{restaurantId}")
  ReadRestaurantExistsDto restaurantExists(@PathVariable UUID restaurantId);
}
