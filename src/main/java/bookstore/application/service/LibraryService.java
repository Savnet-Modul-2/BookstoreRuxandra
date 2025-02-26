package bookstore.application.service;

import bookstore.application.entity.Book;
import bookstore.application.entity.Library;
import bookstore.application.repository.BookRepository;
import bookstore.application.repository.LibraryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;

    public Library create(Library library) {
        if (library.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new library that you want to create");
        }
        return libraryRepository.save(library);
    }

    public Book addBook(Long libraryId, Book book) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException("Library not found"));
        if (!library.getBooks().contains(book)) {
            library.add(book);
            bookRepository.save(book);
            return book;
        }
        throw new EntityExistsException("Book already exists");
    }

    public void remove(Long libraryId, Book book) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException("Library not found"));
        if (!library.getBooks().contains(book)) {
            throw new EntityNotFoundException("Book doesn't exist");
        }
        library.getBooks().remove(book);
        bookRepository.delete(book);
    }
}
