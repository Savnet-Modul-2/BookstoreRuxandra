package bookstore.application.controller;

import bookstore.application.dto.BookDto;
import bookstore.application.entity.Book;
import bookstore.application.mapper.BookMapper;
import bookstore.application.service.BookService;
import bookstore.application.service.LibraryService;
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

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/{libraryId}")
    public ResponseEntity<?> addBook(@PathVariable Long libraryId, @RequestBody BookDto book) {
        Book bookToAdd = BookMapper.mapBookDtoToBook.apply(book);
        Book addedBook = libraryService.addBook(libraryId, bookToAdd);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(addedBook));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> get(@PathVariable Long bookId) {
        Book bookToGet = bookService.findById(bookId);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(bookToGet));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok(books.stream()
                .map(BookMapper.mapBookToBookDto)
                .toList());
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAllPaginated(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Book> books = bookService.getAllPaginated(page, size);
        Page<BookDto> booksDto = books.map(BookMapper.mapBookToBookDto);
        return ResponseEntity.ok(booksDto.get());
    }

    @GetMapping("/title-and-author")
    public ResponseEntity<?> findByTitleAndAuthor(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "author") String author) {
        List<Book> books = bookService.findByTitleAndAuthor(title, author);
        List<BookDto> bookDtos = books.stream()
                .map(BookMapper.mapBookToBookDto)
                .toList();
        return ResponseEntity.ok(bookDtos);
    }

    @PutMapping("/edit/{bookId}")
    public ResponseEntity<?> edit(@PathVariable Long bookId, @RequestBody BookDto book) {
        Book bookToEdit = BookMapper.mapBookDtoToBook.apply(book);
        Book editedBook = bookService.edit(bookId, bookToEdit);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(editedBook));
    }

    @DeleteMapping("/{libraryId}/{bookId}")
    public ResponseEntity<?> removeBook(@PathVariable Long libraryId, @PathVariable Long bookId) {
        Book bookToRemove = bookService.findById(bookId);
        libraryService.remove(libraryId, bookToRemove);
        return ResponseEntity.ok(BookMapper.mapBookToBookDto.apply(bookToRemove));
    }
}
