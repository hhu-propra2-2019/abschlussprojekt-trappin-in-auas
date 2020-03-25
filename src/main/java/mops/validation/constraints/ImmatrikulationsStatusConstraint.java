package mops.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import mops.validation.ModulExistsValidator;

@Documented
@Constraint(validatedBy = ModulExistsValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImmatrikulationsStatusConstraint {
    String message() default "Ungültige Immatrikulation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}