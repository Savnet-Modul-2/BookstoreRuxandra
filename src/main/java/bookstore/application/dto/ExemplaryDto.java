package bookstore.application.dto;

import bookstore.application.entity.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class ExemplaryDto {
    private Long id;

    private String publisher;

    private Integer maximumReservationDuration;
}
