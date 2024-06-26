package project.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class VINValidator implements ConstraintValidator<ValidVinNumber, String> {

    // Correct VIN regex pattern
    private static final String VIN_PATTERN = "^[A-HJ-NPR-Z0-9]{17}$";

    @Override
    public void initialize(ValidVinNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String vin, ConstraintValidatorContext context) {
        if (vin == null || vin.isEmpty()) {
            return false;
        }
        return Pattern.matches(VIN_PATTERN, vin);
    }
}