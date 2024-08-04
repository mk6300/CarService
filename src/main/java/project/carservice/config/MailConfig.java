package project.carservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${MAIL_NAME:syncro6363@abv.bg}")
    private String MAIL_NAME;
    @Value("${MAIL_PASS:@Mitko7279}")
    private String MAIL_PASS;

    @Bean
    public JavaMailSender javaMailSender() {


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.abv.bg");
        mailSender.setPort(465); // 465 for SSL

        mailSender.setUsername(MAIL_NAME);
        mailSender.setPassword(MAIL_PASS);


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.abv.bg");
        props.put("mail.debug", "true");

        return mailSender;
    }
}