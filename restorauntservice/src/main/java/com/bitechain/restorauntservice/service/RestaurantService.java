package com.bitechain.restorauntservice.service;

import com.bitechain.restorauntservice.dto.ReadRestaurantDto;
import com.bitechain.restorauntservice.dto.ReadWorkingHourDto;
import com.bitechain.restorauntservice.dto.WriteRestaurantDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {
  List<ReadRestaurantDto> listRestaurants(Optional<UUID> ownerId, Optional<String> city);
  ReadRestaurantDto getRestaurantById(UUID id);
  ReadRestaurantDto getRestaurantByName(String name);
  ReadRestaurantDto createRestaurant(WriteRestaurantDto restaurantDto);
  ReadRestaurantDto updateRestaurant(UUID id, WriteRestaurantDto restaurantDto);
  void deleteRestaurant(UUID id);
  List<ReadWorkingHourDto> getWorkingHours(UUID restaurantId);
}
