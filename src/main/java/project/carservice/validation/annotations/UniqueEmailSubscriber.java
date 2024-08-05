package project.carservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import project.carservice.validation.UniqueEmailSubscriberValidator;
import project.carservice.validation.UniqueRegistrationValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailSubscriberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailSubscriber {
    String message() default "Email already exist in subscribers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
