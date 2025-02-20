package bookstore.application.exceptions;

public class ProvidedUserIdException extends RuntimeException {
    public ProvidedUserIdException(String message) {
        super(message);
    }
}
