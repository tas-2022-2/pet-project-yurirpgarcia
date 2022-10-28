package br.edu.ifrs.riogrande.tads.onepieceworld.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.ServiceException;

@ControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<Map<String, List<String>>> handleNotFoundException(NotFoundException ex) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("errors", Collections.singletonList(ex.getMessage())));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Map<String, List<String>>> handleIllegalArgumentException(IllegalArgumentException ex) {

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("errors", Collections.singletonList(ex.getMessage())));
  }

  @ExceptionHandler(ServiceException.class)
  protected ResponseEntity<Map<String, List<String>>> handleServiceException(ServiceException ex) {

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(Map.of("errors", Collections.singletonList(ex.getMessage())));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Map<String, List<String>>> handleConstraintViolationException(
      ConstraintViolationException ex) {

    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
    List<String> errors = violations.stream()
        .map(v -> v.getMessage())
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("errors", errors));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    List<String> errors = ex.getFieldErrors().stream()
        .map(e -> e.getDefaultMessage())
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("errors", errors));
  }

}