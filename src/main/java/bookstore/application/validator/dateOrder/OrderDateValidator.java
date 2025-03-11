package bookstore.application.validator.dateOrder;

import bookstore.application.dto.ReservationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderDateValidator implements ConstraintValidator<ValidDateOrder, ReservationDto> {
    @Override
    public void initialize(ValidDateOrder constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ReservationDto reservationDto, ConstraintValidatorContext constraintValidatorContext) {
        return !reservationDto.getStartDate().isAfter(reservationDto.getEndDate());
    }
}
