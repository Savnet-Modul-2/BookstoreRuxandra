package bookstore.application.validator;

import bookstore.application.validator.reservationDate.DateValidator;
import bookstore.application.validator.reservationDate.dateOrder.DateOrderValidator;
import bookstore.application.validator.reservationDate.dateFuture.DateFutureValidator;
import jakarta.validation.GroupSequence;

@GroupSequence({DateValidator.class, DateFutureValidator.class, DateOrderValidator.class})
public interface DateValidatorSequence {
}
