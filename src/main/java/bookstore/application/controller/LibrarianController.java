package bookstore.application.controller;

import bookstore.application.dto.LibrarianDto;
import bookstore.application.dto.TagDto;
import bookstore.application.entity.Librarian;
import bookstore.application.entity.Tag;
import bookstore.application.mapper.LibrarianMapper;
import bookstore.application.mapper.TagMapper;
import bookstore.application.service.LibrarianService;
import bookstore.application.validator.ContactInformationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("{librarianId}/tag/{bookId}")
    public ResponseEntity<?> addTag(@PathVariable Long librarianId, @PathVariable Long bookId, @RequestBody TagDto tag) {
        Tag tagToAdd = TagMapper.mapTagDtoToTag.apply(tag);
        Tag addedTag = librarianService.addTag(librarianId, bookId, tagToAdd);
        return ResponseEntity.ok(TagMapper.mapTagToTagDto.apply(addedTag));
    }

    @GetMapping("{bookId}/tags")
    public ResponseEntity<?> getTags(@PathVariable Long bookId) {
        List<Tag> tags = librarianService.getTags(bookId);
        return ResponseEntity.ok(
                tags
                .stream()
                .map(TagMapper.mapTagToTagDto)
                .toList());
    }

    @PutMapping({"{librarianId}/edit-tag/{bookId}/{tagId}"})
    public ResponseEntity<?> editTag(@PathVariable Long librarianId, @PathVariable Long bookId,
                                     @PathVariable Long tagId, @RequestBody TagDto tag) {
        Tag tagToEdit = TagMapper.mapTagDtoToTag.apply(tag);
        Tag editedTag = librarianService.editTag(librarianId, bookId, tagId, tagToEdit);
        return ResponseEntity.ok(TagMapper.mapTagToTagDto.apply(editedTag));
    }

    @DeleteMapping("{librarianId}/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Long librarianId, @PathVariable Long tagId) {
        librarianService.deleteTag(librarianId, tagId);
        return ResponseEntity.ok().build();
    }
}