package com.bitechain.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
@Data
public class Order {
  @Id
  private UUID id;
  private UUID userId;
  private UUID restaurantId;
  private List<OrderItem> items;
  private OrderStatus status;
  private BigDecimal totalPrice;
  private Instant createdAt;
  private Instant updatedAt;
}
