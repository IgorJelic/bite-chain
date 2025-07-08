package com.bitechain.restorauntservice.dto;

import com.bitechain.restorauntservice.model.DayOfWeek;
import com.bitechain.restorauntservice.model.WorkingHour;
import com.bitechain.restorauntservice.validation.ValidWorkingHourRange;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

@ValidWorkingHourRange
public record WriteWorkingHourDto(
        @NotBlank
        DayOfWeek dayOfWeek,
        @NotBlank
        LocalTime openTime,
        @NotBlank
        LocalTime closeTime
) {
  public WorkingHour getWorkingHour() {
    WorkingHour workingHour = new WorkingHour();
    workingHour.setDayOfWeek(dayOfWeek);
    workingHour.setOpenTime(openTime);
    workingHour.setCloseTime(closeTime);
    return workingHour;
  }
}
