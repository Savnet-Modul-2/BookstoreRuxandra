package bookstore.application.service;
import bookstore.application.entity.User;
import bookstore.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    public void sendEmailVerification(User user) {
        user.setVerificationCode(new Random().nextLong(10000, 100000));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Verification code");
        message.setText("The verification code is: " + user.getVerificationCode());
        mailSender.send(message);
    }
}
