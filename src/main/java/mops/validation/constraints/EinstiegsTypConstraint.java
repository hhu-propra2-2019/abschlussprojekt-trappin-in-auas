package mops.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import mops.validation.EinstiegsTypValidator;

@Documented
@Constraint(validatedBy = EinstiegsTypValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EinstiegsTypConstraint {
  String message() default "Ungültiger Einstiegstyp";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}