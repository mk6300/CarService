package project.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.carservice.repository.CarRepository;
import project.carservice.validation.annotations.UniqueVinNumber;

public class UniqueVinNumberValidator implements ConstraintValidator<UniqueVinNumber, String> {

    private final CarRepository carRepository;

    public UniqueVinNumberValidator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.carRepository.findByVinNumber(value).isEmpty();

    }
}

