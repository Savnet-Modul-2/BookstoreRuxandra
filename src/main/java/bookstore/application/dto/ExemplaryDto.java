package bookstore.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class ExemplaryDto {
    private Long id;

    @NotNull
    private String publisher;

    @NotNull
    private Integer maximumReservationDuration;

    private BookDto book;

    private LocalDateTime updateTime;
}
