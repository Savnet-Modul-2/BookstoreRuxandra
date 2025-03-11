package bookstore.application.validator;

import bookstore.application.validator.dateOrder.DateOrderValidator;
import bookstore.application.validator.dateFuture.DateFutureValidator;
import jakarta.validation.GroupSequence;

@GroupSequence({DateValidator.class, DateFutureValidator.class, DateOrderValidator.class})
public interface DateValidatorSequence {
}
