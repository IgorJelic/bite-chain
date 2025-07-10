package com.bitechain.orderservice.dto;

import java.util.UUID;

public record WriteOrderItemDto(
  UUID itemId,
  int quantity
) {}
