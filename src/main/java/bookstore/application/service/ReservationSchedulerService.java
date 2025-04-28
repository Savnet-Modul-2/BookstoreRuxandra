package bookstore.application.service;

import bookstore.application.entity.Reservation;
import bookstore.application.enums.ReservationStatus;
import bookstore.application.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class ReservationSchedulerService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LibrarianService librarianService;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateReservationStatus() {
        LocalDate today = LocalDate.now();
        List<Reservation> reservationsToBeCanceled = reservationRepository
                .findAllReservationsBeforeADay(today, ReservationStatus.PENDING);
        List<Reservation> reservationsToBeDelayed = reservationRepository
                .findAllReservationsBeforeADay(today, ReservationStatus.IN_PROGRESS);

        reservationsToBeCanceled.forEach(reservation ->
                reservation.setStatus(ReservationStatus.CANCELED));
        reservationsToBeDelayed.forEach(reservation ->
                reservation.setStatus(ReservationStatus.DELAYED));

        reservationRepository.saveAll(reservationsToBeCanceled);
        reservationRepository.saveAll(reservationsToBeDelayed);

        if (!reservationsToBeDelayed.isEmpty()) {
            librarianService.notifyLibrarianDelayedReservations(reservationsToBeDelayed);
        }
    }
}
