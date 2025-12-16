package info.greenpet.service;

import info.greenpet.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(User user) {
        // simple synchronous email; in production make async
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Welcome to GreenPet");
        msg.setText("Hello " + user.getName() + ",\n\nThank you for registering at GreenPet.");
        mailSender.send(msg);
    }
}
