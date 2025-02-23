package bookstore.application.controller;

import bookstore.application.dto.BookDto;
import bookstore.application.entity.Book;
import bookstore.application.mapper.BookMapper;
import bookstore.application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PutMapping("/edit/{bookId}")
    public ResponseEntity<?> editBook(@PathVariable Long bookId, @RequestBody BookDto book) {
        Book bookToEdit = BookMapper.mapBookDtoToBook.apply(book);
        Book editedBook = bookService.edit(bookId, bookToEdit);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(editedBook));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable Long bookId) {
        Book bookToGet = bookService.findById(bookId);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(bookToGet));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok(books.stream()
                .map(BookMapper.mapBookToBookDto)
                .toList());
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAllBooksPaginated(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Book> books = bookService.getAllPaginated(page, size);
        Page<BookDto> booksDto = books.map(BookMapper.mapBookToBookDto);
        return ResponseEntity.ok(booksDto.get());
    }
}
