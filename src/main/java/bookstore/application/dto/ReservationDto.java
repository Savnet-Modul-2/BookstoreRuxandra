package bookstore.application.dto;

import bookstore.application.enums.ReservationStatus;
import bookstore.application.validator.DateValidator;
import bookstore.application.validator.dateFuture.DateFutureValidator;
import bookstore.application.validator.dateFuture.ValidDateFuture;
import bookstore.application.validator.dateOrder.DateOrderValidator;
import bookstore.application.validator.dateOrder.ValidDateOrder;
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
