package bookstore.application.service;

import bookstore.application.entity.Book;
import bookstore.application.entity.User;
import bookstore.application.repository.BookRepository;
import bookstore.application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public Book edit(Long id, Book book) {
        Book bookToEdit = findById(id);
        bookToEdit.setIsbn(book.getIsbn());
        bookToEdit.setTitle(book.getTitle());
        bookToEdit.setAuthor(book.getAuthor());
        bookToEdit.setAppearanceDate(book.getAppearanceDate());
        bookToEdit.setNrOfPages(book.getNrOfPages());
        bookToEdit.setCategory(book.getCategory());
        bookToEdit.setLanguage(book.getLanguage());
        return bookRepository.save(bookToEdit);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Page<Book> getAllPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public List<Book> findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }
}
