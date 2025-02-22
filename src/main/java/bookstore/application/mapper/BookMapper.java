package bookstore.application.mapper;

import bookstore.application.dto.BookDto;
import bookstore.application.entity.Book;

import java.util.function.Function;

public class BookMapper {
    public static final Function<Book, BookDto> mapBookToBookDto = BookMapper::mapBookToBookDto;

    public static final Function<BookDto, Book> mapBookDtoToBook = BookMapper::mapBookDtoToBook;

    private static Book mapBookDtoToBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .appearanceDate(bookDto.getAppearanceDate())
                .nrOfPages(bookDto.getNrOfPages())
                .category(bookDto.getCategory())
                .language(bookDto.getLanguage())
                .build();
    }

    private static BookDto mapBookToBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .appearanceDate(book.getAppearanceDate())
                .nrOfPages(book.getNrOfPages())
                .category(book.getCategory())
                .language(book.getLanguage())
                .build();
    }
}
