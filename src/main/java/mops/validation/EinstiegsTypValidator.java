package mops.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mops.domain.models.EinstiegTyp;
import mops.validation.constraints.EinstiegsTypConstraint;
import mops.validation.constraints.ImmatrikulationsStatusConstraint;

public class EinstiegsTypValidator implements ConstraintValidator<EinstiegsTypConstraint, EinstiegTyp> {
  public void initialize(ImmatrikulationsStatusConstraint constraintAnnotation) {
  }

  @Override
  public boolean isValid(EinstiegTyp einstiegTyp, ConstraintValidatorContext context) {
    return true;
  }
}