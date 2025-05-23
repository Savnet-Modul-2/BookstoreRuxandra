package bookstore.application.service;

import bookstore.application.entity.Librarian;
import bookstore.application.entity.Library;
import bookstore.application.entity.Reservation;
import bookstore.application.entity.User;
import bookstore.application.exceptions.IncorrectPasswordException;
import bookstore.application.exceptions.ProvidedUserIdException;
import bookstore.application.repository.LibrarianRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private EmailService emailService;

    public Librarian create(Librarian librarian) {
        if (librarian.getId() != null) {
            throw new ProvidedUserIdException("You cannot provide an ID to a new librarian that you want to create");
        }
        String password = PasswordService.getMd5(librarian.getPassword());
        librarian.setPassword(password);
        Library createdLibrary = libraryService.create(librarian.getLibrary());
        librarian.setLibrary(createdLibrary);
        return librarianRepository.save(librarian);
    }

    public Librarian login(Librarian librarian) {
        Librarian librarianToLogin = librarianRepository.findByEmail(librarian.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));
        String encodedPassword = PasswordService.getMd5(librarian.getPassword());
        if (encodedPassword.equals(librarianToLogin.getPassword())) {
            return librarianToLogin;
        }
        throw new IncorrectPasswordException("Incorrect password");
    }

    public void notifyLibrarianDelayedReservations(List<Reservation> reservationsDelayed) {
        for (Reservation reservation : reservationsDelayed) {
            String librarianEmail = reservation.getExemplary().getBook().getLibrary().getLibrarian().getEmail();
            User user = reservation.getUser();
            emailService.sendDelayedReservationMail(librarianEmail, user);
        }
    }
}
