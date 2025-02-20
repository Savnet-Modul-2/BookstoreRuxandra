package bookstore.application.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        ErrorDetail error = new ErrorDetail(entityNotFoundException.getMessage());
//        return ResponseEntity.notFound().build(error);
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(ProvidedUserIdException.class)
    public ResponseEntity<?> handleProvidedUserIdException(ProvidedUserIdException providedUserIdException) {
        ErrorDetail error = new ErrorDetail(providedUserIdException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<?> handleIncorrectPasswordException(IncorrectPasswordException incorrectPasswordException) {
        ErrorDetail error = new ErrorDetail(incorrectPasswordException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
