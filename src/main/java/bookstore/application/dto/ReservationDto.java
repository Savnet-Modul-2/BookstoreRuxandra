package bookstore.application.dto;

import bookstore.application.enums.ReservationStatus;
import bookstore.application.validator.reservationDate.DateValidator;
import bookstore.application.validator.reservationDate.dateFuture.DateFutureValidator;
import bookstore.application.validator.reservationDate.dateFuture.ValidDateFuture;
import bookstore.application.validator.reservationDate.dateOrder.DateOrderValidator;
import bookstore.application.validator.reservationDate.dateOrder.ValidDateOrder;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@ValidDateOrder(groups = DateOrderValidator.class)
public class ReservationDto {
    private Long id;

    @NotNull(groups = DateValidator.class)
    @ValidDateFuture(groups = DateFutureValidator.class)
    private LocalDate startDate;

    @NotNull(groups = DateValidator.class)
    @ValidDateFuture(groups = DateFutureValidator.class)
    private LocalDate endDate;

    private ReservationStatus status;

    private UserDto user;

    private ExemplaryDto exemplary;
}
