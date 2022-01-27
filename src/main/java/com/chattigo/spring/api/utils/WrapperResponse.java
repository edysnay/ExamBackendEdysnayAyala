package com.chattigo.spring.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {

  private String message;
  private T body;

  public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
    return new ResponseEntity(this, status);
  }
}
