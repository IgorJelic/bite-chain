package com.bitechain.restorauntservice.service;

import com.bitechain.restorauntservice.dto.ReadWorkingHourDto;
import com.bitechain.restorauntservice.dto.WriteWorkingHourDto;

import java.util.UUID;

public interface WorkingHoursService {
  ReadWorkingHourDto addWorkingHour(UUID restaurantId, WriteWorkingHourDto dto);

  ReadWorkingHourDto updateWorkingHour(UUID hourId, WriteWorkingHourDto dto);

  void deleteWorkingHour(UUID hourId);
}
