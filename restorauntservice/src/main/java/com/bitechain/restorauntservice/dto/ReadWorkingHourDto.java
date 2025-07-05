package com.bitechain.restorauntservice.dto;

import com.bitechain.restorauntservice.model.DayOfWeek;
import com.bitechain.restorauntservice.model.WorkingHour;

import java.time.LocalTime;
import java.util.UUID;

public record ReadWorkingHourDto(
  UUID id,
  DayOfWeek dayOfWeek,
  LocalTime openTime,
  LocalTime closeTime
) {
  public ReadWorkingHourDto(WorkingHour wh) {
    this(
      wh.getId(),
      wh.getDayOfWeek(),
      wh.getOpenTime(),
      wh.getCloseTime()
    );
  }
}
