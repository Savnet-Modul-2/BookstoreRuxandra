package bookstore.application.mapper;

import bookstore.application.dto.LibraryDto;
import bookstore.application.entity.Library;

import java.util.ArrayList;
import java.util.function.Function;

public class LibraryMapper {
    public static final Function<Library, LibraryDto> mapLibraryToLibraryDto = LibraryMapper::mapLibraryToLibraryDto;

    public static final Function<LibraryDto, Library> mapLibraryDtoToLibrary = LibraryMapper::mapLibraryDtoToLibrary;

    private static Library mapLibraryDtoToLibrary(LibraryDto libraryDto) {
        return Library.builder()
                .id(libraryDto.getId())
                .address(libraryDto.getAddress())
                .phoneNumber(libraryDto.getPhoneNumber())
                .books(libraryDto.getBooks() != null ?
                        libraryDto.getBooks().stream()
                                .map(BookMapper.mapBookDtoToBook)
                                .toList() : new ArrayList<>())
                .build();
    }

    private static LibraryDto mapLibraryToLibraryDto(Library library) {
        return LibraryDto.builder()
                .id(library.getId())
                .address(library.getAddress())
                .phoneNumber(library.getPhoneNumber())
                .books(library.getBooks() != null ?
                        library.getBooks().stream()
                                .map(BookMapper.mapBookToBookDto)
                                .toList() : new ArrayList<>())
                .build();
    }
}
