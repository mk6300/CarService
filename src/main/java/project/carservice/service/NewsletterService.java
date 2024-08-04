package project.carservice.service;

import project.carservice.model.dto.SubscriberDTO;


public interface NewsletterService {
    void subscribe(SubscriberDTO subscriberDTO);

    void unsubscribe(String email);
}
