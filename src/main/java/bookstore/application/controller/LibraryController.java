package bookstore.application.controller;

import bookstore.application.dto.BookDto;
import bookstore.application.entity.Book;
import bookstore.application.mapper.BookMapper;
import bookstore.application.service.BookService;
import bookstore.application.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookService bookService;

    @PostMapping("/{libraryId}")
    public ResponseEntity<?> addBook(@PathVariable Long libraryId, @RequestBody BookDto book) {
        Book bookToAdd = BookMapper.mapBookDtoToBook.apply(book);
        Book addedBook = libraryService.addBook(libraryId, bookToAdd);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(addedBook));
    }

    @DeleteMapping("/{libraryId}/{bookId}")
    public ResponseEntity<?> removeBook(@PathVariable Long libraryId, @PathVariable Long bookId) {
        Book bookToRemove = bookService.findById(bookId);
        libraryService.remove(libraryId, bookToRemove);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(bookToRemove));
    }
}
