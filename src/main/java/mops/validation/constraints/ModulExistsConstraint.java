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
public @interface ModulExistsConstraint {
    String message() default "Modul existiert nicht";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}