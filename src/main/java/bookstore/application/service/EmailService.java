package bookstore.application.service;

import bookstore.application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendEmailVerification(User user) {
        user.setVerificationCode(new Random().nextLong(10000, 100000));
        sendEmail(user.getEmail(), "Verification code", "The verification code is: " + user.getVerificationCode());
    }

    public void sendDelayedReservationMail(String librarianEmail, User user) {
        sendEmail(librarianEmail,
                "Delayed Reservation",
                "User " + user.getFirstName() + " " + user.getLastName() + " has a delayed reservation");
    }
}
