package com.bitechain.restorauntservice.service;

import com.bitechain.restorauntservice.client.UserClient;
import com.bitechain.restorauntservice.dto.ReadRestaurantDto;
import com.bitechain.restorauntservice.dto.ReadRestaurantExistsDto;
import com.bitechain.restorauntservice.dto.ReadWorkingHourDto;
import com.bitechain.restorauntservice.dto.WriteRestaurantDto;
import com.bitechain.restorauntservice.exception.RestaurantConflictException;
import com.bitechain.restorauntservice.exception.RestaurantNotFoundException;
import com.bitechain.restorauntservice.exception.UserNotFoundException;
import com.bitechain.restorauntservice.repository.RestaurantRepository;
import com.bitechain.restorauntservice.repository.specification.RestaurantSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
  private final RestaurantRepository restaurantRepository;
  private final UserClient userClient;

  @Override
  public List<ReadRestaurantDto> listRestaurants(UUID ownerId, String city) {
    var spec = RestaurantSpecifications.all()
            .and(RestaurantSpecifications.byOwnerId(ownerId))
            .and(RestaurantSpecifications.byCity(city));

    var restaurants = restaurantRepository.findAll(spec);
    return restaurants.stream().map(ReadRestaurantDto::new)
            .collect(Collectors.toList());
  }

  @Override
  public ReadRestaurantDto getRestaurantById(UUID id) {
    var restaurant = restaurantRepository.findById(id);
    if (restaurant.isEmpty()) {
      throw new RestaurantNotFoundException("Restaurant with id " + id + " not found");
    }
    return new ReadRestaurantDto(restaurant.get());
  }

  @Override
  public ReadRestaurantExistsDto restaurantExists(UUID restaurantId) {
    var exists = restaurantRepository.existsById(restaurantId);
    return new ReadRestaurantExistsDto(restaurantId, exists);
  }

  @Override
  public ReadRestaurantDto getRestaurantByName(String name) {
    var restaurant = restaurantRepository.findByName(name);
    if (restaurant.isEmpty()) {
      throw new RestaurantNotFoundException("Restaurant with name " + name + " not found");
    }
    return new ReadRestaurantDto(restaurant.get());
  }

  @Override
  public ReadRestaurantDto createRestaurant(WriteRestaurantDto restaurantDto) {
    if (restaurantRepository.existsByName(restaurantDto.name())) {
      throw new RestaurantConflictException("Restaurant with name " + restaurantDto.name() + " already exists");
    }

    var userExists = userClient.userExists(restaurantDto.ownerId());
    if (!userExists.exists()) {
      throw new UserNotFoundException("Owner with id " + restaurantDto.ownerId() + " does not exist");
    }

    var restaurant = restaurantDto.getRestaurant();
    restaurant = restaurantRepository.save(restaurant);
    return new ReadRestaurantDto(restaurant);
  }

  @Override
  public ReadRestaurantDto updateRestaurant(UUID id, WriteRestaurantDto restaurantDto) {
    var optionalRestaurant = restaurantRepository.findById(id);
    if (optionalRestaurant.isEmpty()) {
      throw new RestaurantNotFoundException("Restaurant with id " + id + " not found");
    }
    if (restaurantRepository.existsByName(restaurantDto.name()) &&
            !optionalRestaurant.get().getName().equals(restaurantDto.name())) {
      throw new RestaurantConflictException("Restaurant with name " + restaurantDto.name() + " already exists");
    }

    var existingRestaurant = optionalRestaurant.get();
    existingRestaurant.setName(restaurantDto.name());
    existingRestaurant.setDescription(restaurantDto.description());
    existingRestaurant.setAddress(restaurantDto.address().getAddress());
    existingRestaurant.setWebsite(restaurantDto.website());
    existingRestaurant.setImageUrl(restaurantDto.imageUrl());
    existingRestaurant.setOwnerId(restaurantDto.ownerId());
    existingRestaurant = restaurantRepository.save(existingRestaurant);

    return new ReadRestaurantDto(existingRestaurant);
  }

  @Override
  public void deleteRestaurant(UUID id) {
    restaurantRepository.deleteById(id);
  }

  @Override
  public List<ReadWorkingHourDto> getWorkingHours(UUID restaurantId) {
    var restaurant = restaurantRepository.findById(restaurantId);
    if (restaurant.isEmpty()) {
      throw new RestaurantNotFoundException("Restaurant with id " + restaurantId + " not found");
    }
    return restaurant.get().getWorkingHours().stream()
            .sorted(Comparator.comparingInt(wh -> wh.getDayOfWeek().ordinal()))
            .map(ReadWorkingHourDto::new)
            .collect(Collectors.toList());
  }


}
