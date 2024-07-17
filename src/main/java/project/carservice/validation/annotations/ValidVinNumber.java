package project.carservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import project.carservice.validation.VINValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VINValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVinNumber {
    String message() default "Invalid VIN number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}