package project.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.carservice.repository.CarRepository;
import project.carservice.validation.annotations.UniqueRegistration;

public class UniqueRegistrationValidator implements ConstraintValidator<UniqueRegistration, String> {
    private final CarRepository carRepository;

    public UniqueRegistrationValidator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.carRepository.findByRegistration(value).isEmpty();

    }
}