package bookstore.application.service;

import bookstore.application.entity.Book;
import bookstore.application.entity.Exemplary;
import bookstore.application.repository.BookRepository;
import bookstore.application.repository.ExemplaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaryService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExemplaryRepository exemplaryRepository;

    public List<Exemplary> create(Exemplary exemplary, Long bookId, Integer exemplarsNumber) {
        List<Exemplary> exemplars = new ArrayList<>();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        for (int i = 0; i < exemplarsNumber; i++) {
            Exemplary newExemplary = new Exemplary();
            newExemplary.setPublisher(exemplary.getPublisher());
            newExemplary.setMaximumReservationDuration(exemplary.getMaximumReservationDuration());
            book.addExemplary(newExemplary);
            exemplars.add(exemplaryRepository.save(newExemplary));
        }
        return exemplars;
    }

    public List<Exemplary> getAll(Long bookId) {
        return exemplaryRepository.findByBookId(bookId);
    }

    public Exemplary findFirstAvailable(Long bookId, LocalDate startDate, LocalDate endDate) {
        return exemplaryRepository.findFirstAvailable(bookId, startDate, endDate);
    }

    public void delete(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Exemplary exemplary = book.getExemplars().getFirst();
        book.getExemplars().remove(exemplary);
        exemplaryRepository.delete(exemplary);
    }
}
