package bookstore.application.service;

import bookstore.application.entity.*;
import bookstore.application.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {
    @Autowired
    private ExemplaryRepository exemplaryRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public Reservation create(Long bookId, Long userId, Reservation reservation) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Exemplary exemplary = exemplaryRepository
                .findFirstAvailable(bookId, reservation.getStartDate(), reservation.getEndDate());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (exemplary == null) {
            throw new EntityNotFoundException("No exemplary available for this book");
        }
        exemplary.setUpdateTime(LocalDateTime.now());
        exemplaryRepository.save(exemplary);
        reservation.setExemplary(exemplary);
        user.add(reservation);
        exemplary.add(reservation);
        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation updateReservation(Long reservationId, Long librarianId, Reservation newReservation) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        if (!librarianId.equals(reservation.getExemplary().getBook().getLibrary().getLibrarian().getId())) {
            throw new EntityNotFoundException("Librarian is wrong for the reservation");
        }
        if (!reservation.getStatus().isNextStatePossible(newReservation.getStatus())) {
            throw new EntityNotFoundException("Cannot update status of reservation");
        }
        reservation.setStartDate(newReservation.getStartDate());
        reservation.setEndDate(newReservation.getEndDate());
        reservation.setStatus(newReservation.getStatus());
        return reservationRepository.save(reservation);
    }

    public void returnBook(Reservation reservation) {
        Reservation reservationToDelete = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        reservationRepository.delete(reservationToDelete);
    }
}
