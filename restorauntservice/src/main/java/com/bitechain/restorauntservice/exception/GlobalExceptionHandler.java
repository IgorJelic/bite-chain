package com.bitechain.restorauntservice.exception;

import com.bitechain.restorauntservice.dto.ReadProblemDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(RestaurantNotFoundException.class)
  public ResponseEntity<ReadProblemDetailDto> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
    var problemDetail = new ReadProblemDetailDto("Restaurant Not Found", ex.getMessage(), HttpStatus.NOT_FOUND);
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problemDetail);
  }

  @ExceptionHandler(RestaurantBadRequestException.class)
  public ResponseEntity<ReadProblemDetailDto> handleRestaurantBadRequestException(RestaurantBadRequestException ex) {
    var problemDetail = new ReadProblemDetailDto("Bad Request", ex.getMessage(), HttpStatus.BAD_REQUEST);
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(problemDetail);
  }

  @ExceptionHandler(RestaurantConflictException.class)
  public ResponseEntity<ReadProblemDetailDto> handleRestaurantConflictException(RestaurantConflictException ex) {
    var problemDetail = new ReadProblemDetailDto("Conflict", ex.getMessage(), HttpStatus.CONFLICT);
    return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(problemDetail);
  }

  @ExceptionHandler(WorkingHoursNotFoundException.class)
  public ResponseEntity<ReadProblemDetailDto> handleWorkingHoursNotFoundException(WorkingHoursNotFoundException ex) {
    var problemDetail = new ReadProblemDetailDto("Working Hours Not Found", ex.getMessage(), HttpStatus.NOT_FOUND);
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problemDetail);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ReadProblemDetailDto> handleInternalServerException(Exception ex) {
    var problemDetail = new ReadProblemDetailDto("Internal Server Error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(problemDetail);
  }
}
