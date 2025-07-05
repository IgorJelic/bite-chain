package com.bitechain.restorauntservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(
  name = "working_hours",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"restaurant_id", "day_of_week"})
  }
)
@Data
public class WorkingHour {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;
  private LocalTime openTime;
  private LocalTime closeTime;
}
