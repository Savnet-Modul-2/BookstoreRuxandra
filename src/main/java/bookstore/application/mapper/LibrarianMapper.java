package bookstore.application.mapper;

import bookstore.application.dto.LibrarianDto;
import bookstore.application.entity.Librarian;

import java.util.function.Function;

public class LibrarianMapper {
    public static final Function<Librarian, LibrarianDto> mapLibrarianToLibrarianDto = LibrarianMapper::mapLibrarianToLibrarianDto;

    public static final Function<LibrarianDto, Librarian> mapLibrarianDtoToLibrarian = LibrarianMapper::mapLibrarianDtoToLibrarian;

    private static Librarian mapLibrarianDtoToLibrarian(LibrarianDto librarianDto) {
        return Librarian.builder()
                .id(librarianDto.getId())
                .name(librarianDto.getName())
                .email(librarianDto.getEmail())
                .password(librarianDto.getPassword())
                .library(LibraryMapper.mapLibraryDtoToLibrary.apply(librarianDto.getLibrary()))
                .build();
    }

    private static LibrarianDto mapLibrarianToLibrarianDto(Librarian librarian) {
        return LibrarianDto.builder()
                .id(librarian.getId())
                .name(librarian.getName())
                .email(librarian.getEmail())
                .password(librarian.getPassword())
                .library(LibraryMapper.mapLibraryToLibraryDto.apply(librarian.getLibrary()))
                .build();
    }
}
