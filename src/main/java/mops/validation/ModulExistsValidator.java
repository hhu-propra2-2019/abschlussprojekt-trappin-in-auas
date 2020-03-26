package mops.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import mops.domain.models.Modul;
import mops.services.ModulService;
import mops.validation.constraints.ModulExistsConstraint;

public class ModulExistsValidator implements ConstraintValidator<ModulExistsConstraint, Modul> {

  @Autowired
  private transient ModulService modulService;

  public void initialize(ModulExistsConstraint constraintAnnotation) {
  }

  @Override
  public boolean isValid(Modul modul, ConstraintValidatorContext context) {
    if (modul != null) {
      return modulService.modulExists(modul);
    }
    return false;
  }
}