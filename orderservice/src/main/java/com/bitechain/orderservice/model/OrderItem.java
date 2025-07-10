package com.bitechain.orderservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItem {
  private UUID itemId;
  private String name;
  private BigDecimal price;
  private int quantity;
}
