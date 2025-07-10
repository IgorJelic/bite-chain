package com.bitechain.orderservice.repository;

import com.bitechain.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
  List<Order> findByUserId(UUID userId);
  List<Order> findByRestaurantId(UUID restaurantId);
}
