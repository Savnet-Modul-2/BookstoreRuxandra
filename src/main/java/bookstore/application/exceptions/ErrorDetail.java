package bookstore.application.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetail {
    private LocalDateTime time;

    private String errorMessage;

    public ErrorDetail(String errorMessage) {
        this.time = LocalDateTime.now();
        this.errorMessage = errorMessage;
    }
}
