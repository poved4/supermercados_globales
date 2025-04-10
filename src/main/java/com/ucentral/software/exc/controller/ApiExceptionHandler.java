package com.ucentral.software.exc.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ucentral.software.exc.model.BadRequestException;
import com.ucentral.software.exc.model.InternalServerErrorException;
import com.ucentral.software.exc.model.NoContentException;
import com.ucentral.software.exc.model.NotFoundException;
import com.ucentral.software.exc.model.NotImplementedException;
import com.ucentral.software.exc.model.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NoContentException.class)
  public ResponseEntity<?> handleException(NoContentException e) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<?> handleException(BadRequestException e) {
    return new ResponseEntity<>(
        Map.of("error", e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<?> handleException(UnauthorizedException e) {
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleException(NotFoundException e) {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NotImplementedException.class)
  public ResponseEntity<?> handleException(NotImplementedException e) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<?> handleException(InternalServerErrorException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
