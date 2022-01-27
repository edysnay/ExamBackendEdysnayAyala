package com.chattigo.spring.api.config;

import com.chattigo.spring.api.exceptions.GeneralServiceException;
import com.chattigo.spring.api.exceptions.NoDataFoundException;
import com.chattigo.spring.api.exceptions.ValidateServiceException;
import com.chattigo.spring.api.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> all(Exception e, WebRequest request) {
    log.error(e.getMessage(), e);
    WrapperResponse<?> response = new WrapperResponse<>("Internal Server Error", "[]");
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ValidateServiceException.class)
  public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request) {
    log.info(e.getMessage(), e);
    WrapperResponse<?> response = new WrapperResponse<>(e.getMessage(), "[]");
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> constraintViolationeException(ConstraintViolationException e, WebRequest request) {
    StringBuilder message = new StringBuilder();
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    violations.stream()
        .forEach(violation -> message.append(violation.getMessage().concat(";")));
    log.info(message.toString(), e);
    WrapperResponse<?> response = new WrapperResponse<>(message.toString(), "[]");
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoDataFoundException.class)
  public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request) {
    log.info(e.getMessage(), e);
    WrapperResponse<?> response = new WrapperResponse<>(e.getMessage(), "[]");
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(GeneralServiceException.class)
  public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request) {
    log.error(e.getMessage(), e);
    WrapperResponse<?> response = new WrapperResponse<>("Internal Server Error", "[]");
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
