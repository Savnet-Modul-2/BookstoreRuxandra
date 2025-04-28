package bookstore.application.service;

import bookstore.application.entity.Book;
import bookstore.application.entity.User;
import bookstore.application.exceptions.IncorrectPasswordException;
import bookstore.application.exceptions.ProvidedUserIdException;
import bookstore.application.repository.BookRepository;
import bookstore.application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BookRepository bookRepository;


    public User create(User user) {
        if (user.getId() != null) {
            throw new ProvidedUserIdException("You cannot provide an ID to a new user that you want to create");
        }
        String password = PasswordService.getMd5(user.getPassword());
        user.setPassword(password);
        emailService.sendEmailVerification(user);
        return userRepository.save(user);
    }

    public User addBookToWishlist(Long userId, Book bookToAdd){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("The user does not exist"));
        user.add(bookToAdd);
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

    public void removeBookFromWishlist(Long userId, Book book){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("The user does not exist"));
        user.remove(book);
        userRepository.save(user);
    }

    public List<Book> getWishlist(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("The user does not exist"));
        return user.getWishlist();
    }

    public Page<Book> getWishlistPaginated(Long userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public User login(User user) {
        User userToLogin = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        String encodedPassword = PasswordService.getMd5(user.getPassword());
        if (encodedPassword.equals(userToLogin.getPassword())) {
            return userToLogin;
        }
        throw new IncorrectPasswordException("Incorrect password");
    }
}
