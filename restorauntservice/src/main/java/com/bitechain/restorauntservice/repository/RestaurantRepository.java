package com.bitechain.restorauntservice.repository;

import com.bitechain.restorauntservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID>, JpaSpecificationExecutor<Restaurant> {
  boolean existsByName(String name);

  Optional<Restaurant> findByName(String name);
}
