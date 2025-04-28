package bookstore.application.service;

import bookstore.application.entity.*;
import bookstore.application.exceptions.IncorrectPasswordException;
import bookstore.application.exceptions.ProvidedUserIdException;
import bookstore.application.repository.BookRepository;
import bookstore.application.repository.LibrarianRepository;
import bookstore.application.repository.TagRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TagRepository tagRepository;

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

    public Tag addTag(Long librarianId, Long bookId, Tag tag){
        if(tagRepository.findByName(tag.getName()).isPresent()){
            throw new EntityExistsException("Tag already exists");
        }
        Librarian librarian = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        tag.setBook(book);
        tag.setLibrarian(librarian);
        book.getTags().add(tag);
        librarian.getTags().add(tag);
        tag.setTime(LocalDateTime.now());
        return tagRepository.save(tag);
    }

    public Tag editTag(Long librarianId, Long bookId, Long tagId, Tag tagToEdit){
        Librarian librarian = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        if(!librarianId.equals(book.getLibrary().getLibrarian().getId())){
            throw new EntityNotFoundException("Librarian does not match with the book");
        }
        if(book.getTags().contains(tag)){
            throw new EntityExistsException("Tag already exists");
        }
        tag.setName(tagToEdit.getName());
        tag.setTime(LocalDateTime.now());
        tag.setDescription(tagToEdit.getDescription());
        tag.setBook(book);
        tag.setLibrarian(librarian);
        return tagRepository.save(tag);
    }

    public List<Tag> getTags(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return book.getTags();
    }

    public void deleteTag(Long librarianId, Long tagId){
        Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        if(!tag.getLibrarian().getId().equals(librarianId)){
            throw new EntityNotFoundException("Librarian does not match with the book");
        }
        tagRepository.deleteById(tagId);
    }

    public void notifyLibrarianDelayedReservations(List<Reservation> reservationsDelayed) {
        for (Reservation reservation : reservationsDelayed) {
            String librarianEmail = reservation.getExemplary().getBook().getLibrary().getLibrarian().getEmail();
            User user = reservation.getUser();
            emailService.sendDelayedReservationMail(librarianEmail, user);
        }
    }
}
