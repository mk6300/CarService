package project.carservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import project.carservice.validation.UniqueVinNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueVinNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueVinNumber{
    String message() default "Car with this VIN number already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}