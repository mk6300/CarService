package project.carservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.carservice.repository.SubscriberRepository;
import project.carservice.validation.annotations.UniqueEmailSubscriber;

public class UniqueEmailSubscriberValidator implements ConstraintValidator<UniqueEmailSubscriber, String> {

        private final SubscriberRepository subscriberRepository;

    public UniqueEmailSubscriberValidator(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return !this.subscriberRepository.existsBySubsEmail(value);
        }

}
