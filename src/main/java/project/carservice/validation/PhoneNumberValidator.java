package project.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^([+]?359)|0?(|-| )8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return pattern.matcher(phone).matches();
    }
}