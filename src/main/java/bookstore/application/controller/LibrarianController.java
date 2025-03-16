package bookstore.application.controller;

import bookstore.application.dto.LibrarianDto;
import bookstore.application.entity.Librarian;
import bookstore.application.mapper.LibrarianMapper;
import bookstore.application.service.LibrarianService;
import bookstore.application.validator.ContactInformationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping
    public ResponseEntity<?> create(@Validated(ContactInformationSequence.class)
                                    @RequestBody LibrarianDto librarian) {
        Librarian mappedLibrarian = LibrarianMapper.mapLibrarianDtoToLibrarian.apply(librarian);
        Librarian createdLibrarian = librarianService.create(mappedLibrarian);
        LibrarianDto createdLibrarianDto = LibrarianMapper.mapLibrarianToLibrarianDto.apply(createdLibrarian);
        return ResponseEntity.ok(createdLibrarianDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LibrarianDto librarian) {
        Librarian mappedLibrarian = LibrarianMapper.mapLibrarianDtoToLibrarian.apply(librarian);
        Librarian loginLibrarian = librarianService.login(mappedLibrarian);
        LibrarianDto loginLibrarianDto = LibrarianMapper.mapLibrarianToLibrarianDto.apply(loginLibrarian);
        return ResponseEntity.ok(loginLibrarianDto);
    }
}