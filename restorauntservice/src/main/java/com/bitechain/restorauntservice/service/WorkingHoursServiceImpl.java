package com.bitechain.restorauntservice.service;

import com.bitechain.restorauntservice.dto.ReadWorkingHourDto;
import com.bitechain.restorauntservice.dto.WriteWorkingHourDto;
import com.bitechain.restorauntservice.exception.RestaurantConflictException;
import com.bitechain.restorauntservice.exception.RestaurantNotFoundException;
import com.bitechain.restorauntservice.exception.WorkingHoursNotFoundException;
import com.bitechain.restorauntservice.repository.RestaurantRepository;
import com.bitechain.restorauntservice.repository.WorkingHoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkingHoursServiceImpl implements WorkingHoursService {
  private final WorkingHoursRepository workingHoursRepository;
  private final RestaurantRepository restaurantRepository;

  @Override
  public ReadWorkingHourDto addWorkingHour(UUID restaurantId, WriteWorkingHourDto dto) {
    var restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId));
    if (restaurant.getWorkingHours().stream().anyMatch(wh -> wh.getDayOfWeek() == dto.dayOfWeek())) {
      throw new RestaurantConflictException("Working hour for " + dto.dayOfWeek() + " already exists for restaurant with id: " + restaurantId);
    }
    var workingHour = dto.getWorkingHour();
    restaurant.addWorkingHour(workingHour);
    restaurantRepository.save(restaurant);
    return new ReadWorkingHourDto(workingHour);
  }

  @Override
  public ReadWorkingHourDto updateWorkingHour(UUID hourId, WriteWorkingHourDto dto) {
    var workingHour = workingHoursRepository.findById(hourId)
            .orElseThrow(() -> new WorkingHoursNotFoundException("Working hour not found with id: " + hourId));
    workingHour.setOpenTime(dto.openTime());
    workingHour.setCloseTime(dto.closeTime());
    workingHoursRepository.save(workingHour);
    return new ReadWorkingHourDto(workingHour);
  }

  @Override
  public void deleteWorkingHour(UUID hourId) {
    var workingHour = workingHoursRepository.findById(hourId)
            .orElseThrow(() -> new WorkingHoursNotFoundException("Working hour not found with id: " + hourId));
    workingHoursRepository.delete(workingHour);
  }
}
