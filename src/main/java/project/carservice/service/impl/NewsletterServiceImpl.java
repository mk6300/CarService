package project.carservice.service.impl;

import org.springframework.stereotype.Service;
import project.carservice.model.dto.SubscriberDTO;
import project.carservice.model.entity.Subscriber;
import project.carservice.repository.SubscriberRepository;
import project.carservice.service.MailService;
import project.carservice.service.NewsletterService;

import java.util.logging.Logger;

@Service
public class NewsletterServiceImpl implements NewsletterService {
    private final SubscriberRepository subscriberRepository;
    private final MailService mailService;
    private final Logger LOGGER = Logger.getLogger(NewsletterServiceImpl.class.getName());

    public NewsletterServiceImpl(SubscriberRepository subscriberRepository, MailService mailService) {
        this.subscriberRepository = subscriberRepository;
        this.mailService = mailService;
    }
    @Override
    public void subscribe (SubscriberDTO subscriberDTO) {
        if (!subscriberRepository.existsBySubsEmail(subscriberDTO.getSubsEmail())) {
            Subscriber subscriber= new Subscriber();
            subscriber.setSubsEmail(subscriberDTO.getSubsEmail());
            subscriberRepository.save(subscriber);
            sendConfirmationEmail(subscriber.getSubsEmail());
        }
    }
    private void sendConfirmationEmail(String email) {
        mailService.sendMail(email, "Subscription Confirmation",
                "Thank you for subscribing to Car Service newsletter!");

        LOGGER.info("Email sent to: " + email);
    }


    @Override
    public void unsubscribe(String email) {
        if (subscriberRepository.existsBySubsEmail(email)) {
            subscriberRepository.deleteBySubsEmail(email);
            sendUnsubscribeEmail(email);
        }
    }

    private void sendUnsubscribeEmail(String email) {
        mailService.sendMail(email, "Unsubscribe Confirmation",
                "You have successfully unsubscribed from Car Service newsletter!");
        LOGGER.info("Email sent to: " + email);
    }
}
