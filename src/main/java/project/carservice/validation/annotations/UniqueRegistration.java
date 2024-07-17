package project.carservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import project.carservice.validation.UniqueRegistrationValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueRegistrationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRegistration{
    String message() default "Car with this registration already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}