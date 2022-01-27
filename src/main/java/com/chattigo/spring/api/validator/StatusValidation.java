package com.chattigo.spring.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class StatusValidation implements ConstraintValidator<Status, String> {

  @Override
  public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
    final Set<String> st = Set.of("offline", "online");
    return st.contains(status);
  }
}