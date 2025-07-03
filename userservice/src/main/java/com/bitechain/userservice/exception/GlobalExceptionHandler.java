package com.bitechain.userservice.exception;

import com.bitechain.userservice.dto.ReadProblemDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ReadProblemDetailDto> handleUserNotFoundException(UserNotFoundException ex) {
    var problemDetail = new ReadProblemDetailDto("User Not Found", ex.getMessage(), HttpStatus.NOT_FOUND);
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problemDetail);
  }

  @ExceptionHandler(UserBadRequestException.class)
  public ResponseEntity<ReadProblemDetailDto> handleUserBadRequestException(UserBadRequestException ex) {
    var problemDetail = new ReadProblemDetailDto("Bad Request", ex.getMessage(), HttpStatus.BAD_REQUEST);
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(problemDetail);
  }

  @ExceptionHandler(UserConflictException.class)
  public ResponseEntity<ReadProblemDetailDto> handleUserConflictException(UserConflictException ex) {
    var problemDetail = new ReadProblemDetailDto("Conflict", ex.getMessage(), HttpStatus.CONFLICT);
    return ResponseEntity
            .status(HttpStatus.CONFLICT)
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
