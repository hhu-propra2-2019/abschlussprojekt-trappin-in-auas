package mops.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mops.domain.models.ImmatrikulationsStatus;
import mops.validation.constraints.ImmatrikulationsStatusConstraint;

public class ImmatrikulationsStatusValidator implements ConstraintValidator<ImmatrikulationsStatusConstraint, ImmatrikulationsStatus> {
  public void initialize(ImmatrikulationsStatusConstraint constraintAnnotation) {
  }

  @Override
  public boolean isValid(ImmatrikulationsStatus status, ConstraintValidatorContext context) {
    if(!status.isStatus()){ //status is inverted at first
      return !status.getFachrichtung().isBlank();
    }
    return true;
  }
}