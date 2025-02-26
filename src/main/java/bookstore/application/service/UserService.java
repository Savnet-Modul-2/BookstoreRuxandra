package bookstore.application.service;

import bookstore.application.entity.User;
import bookstore.application.exceptions.IncorrectPasswordException;
import bookstore.application.exceptions.ProvidedUserIdException;
import bookstore.application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    public User create(User user) {
        if (user.getId() != null) {
            throw new ProvidedUserIdException("You cannot provide an ID to a new user that you want to create");
        }
        String password = PasswordService.getMd5(user.getPassword());
        user.setPassword(password);
        emailService.sendEmailVerification(user);
        return userRepository.save(user);
    }

    public User verifyCode(String email, Long code) {
        User userToVerify = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!Objects.equals(userToVerify.getVerificationCode(), code)) {
            throw new RuntimeException("Incorrect verification code");
        }
        userToVerify.setVerifiedAccount(true);
        return userRepository.save(userToVerify);
    }

    public User login(User user) {
        User userToLogin = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        String encodedPassword = PasswordService.getMd5(user.getPassword());
        assert userToLogin != null;
        if (encodedPassword.equals(userToLogin.getPassword())) {
            return userToLogin;
        }
        throw new IncorrectPasswordException("Incorrect password");
    }
}
