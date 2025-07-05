package com.bitechain.restorauntservice.validation;

import com.bitechain.restorauntservice.dto.WriteWorkingHourDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WorkingHourTimeRangeValidator implements ConstraintValidator<ValidWorkingHourRange, WriteWorkingHourDto> {

  @Override
  public boolean isValid(WriteWorkingHourDto writeWorkingHourDto, ConstraintValidatorContext constraintValidatorContext) {
    boolean isValid = writeWorkingHourDto.closeTime().isAfter(writeWorkingHourDto.openTime());

    if (!isValid) {
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext.buildConstraintViolationWithTemplate("closeTime must be after openTime")
          .addPropertyNode("closeTime")
          .addConstraintViolation();
    }

    return isValid;
  }
}
