package com.chattigo.spring.api.validator;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StatusValidation.class)
@Documented
public @interface Status {

  String message();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
