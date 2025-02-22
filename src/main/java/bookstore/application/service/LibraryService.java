package bookstore.application.service;

import bookstore.application.entity.Library;
import bookstore.application.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public Library create(Library library) {
        if (library.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new library that you want to create");
        }
        return libraryRepository.save(library);
    }
}
