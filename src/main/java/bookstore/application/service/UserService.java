package bookstore.application.service;

import bookstore.application.entity.User;
import bookstore.application.repository.UserRepository;
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
            throw new RuntimeException("You cannot provide an ID to a new user that you want to create");
        }
        String password = PasswordService.getMd5(user.getPassword());
        user.setPassword(password);
        emailService.sendEmailVerification(user);
        return userRepository.save(user);
    }

    public User verifyCode(Long id, Long code){
        User userToVerify = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        if(!Objects.equals(userToVerify.getVerificationCode(), code)){
            throw new RuntimeException("Incorrect verification code");
        }
        userToVerify.setVerifiedAccount(true);
        return userRepository.save(userToVerify);
    }
}
