package com.bitechain.orderservice.model;

public enum OrderStatus {
    PENDING,
    ACCEPTED,
    PREPARING,
    READY_FOR_PICKUP,
    DELIVERING,
    COMPLETED,
    CANCELLED
}
