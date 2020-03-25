package mops.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mops.domain.models.ImmartikulationsStatus;
import mops.validation.constraints.ImmatrikulationsStatusConstraint;

public class ImmatrikulationsStatusValidator implements ConstraintValidator<ImmatrikulationsStatusConstraint, ImmartikulationsStatus> {
  public void initialize(ImmatrikulationsStatusConstraint constraintAnnotation) {
  }

  @Override
  public boolean isValid(ImmartikulationsStatus status, ConstraintValidatorContext context) {
    if(status.isStatus()){
      return !status.getFachrichtung().isBlank();
    }
    return true;
  }
}