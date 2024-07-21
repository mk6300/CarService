package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.SubscriberDTO;
import project.carservice.model.entity.Subscriber;
import project.carservice.repository.SubscriberRepository;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final ModelMapper modelMapper;
    private final JavaMailSender mailSender;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, ModelMapper modelMapper, JavaMailSender mailSender) {
        this.subscriberRepository = subscriberRepository;
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
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
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Subscription Confirmation");
        message.setText("Thank you for subscribing to Car Service newsletter!");
        mailSender.send(message);
    }
}
