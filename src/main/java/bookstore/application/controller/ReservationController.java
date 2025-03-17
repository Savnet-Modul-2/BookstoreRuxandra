package bookstore.application.controller;

import bookstore.application.dto.ReservationDto;
import bookstore.application.entity.Reservation;
import bookstore.application.mapper.ReservationMapper;
import bookstore.application.service.ReservationService;
import bookstore.application.validator.DateValidatorSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserve/{bookId}/{userId}")
    public ResponseEntity<?> reserve(@PathVariable Long bookId,
                                     @PathVariable Long userId,
                                     @Validated(DateValidatorSequence.class)
                                     @RequestBody ReservationDto reservation) {
        Reservation newReservation = reservationService.create(bookId, userId,
                ReservationMapper.mapReservationDtoToReservation.apply(reservation));
        return ResponseEntity.ok(ReservationMapper.mapReservationToReservationDto.apply(newReservation));
    }

    @PutMapping("/update-reservation/{reservationId}/{librarianId}")
    public ResponseEntity<?> updateReservationStatus(@PathVariable Long reservationId,
                                                     @PathVariable Long librarianId,
                                                     @Validated(DateValidatorSequence.class)
                                                     @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.updateReservation(reservationId, librarianId,
                ReservationMapper.mapReservationDtoToReservation.apply(reservationDto));
        return ResponseEntity.ok(ReservationMapper.mapReservationToReservationDto.apply(reservation));
    }
}
