package bookstore.application.repository;

import bookstore.application.entity.Reservation;
import bookstore.application.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
        SELECT r FROM reservation r
        WHERE r.startDate < :today
        AND r.status = :status
    """)
    List<Reservation> findAllReservations(LocalDate today, ReservationStatus status);
}
