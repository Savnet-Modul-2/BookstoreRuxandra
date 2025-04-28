package bookstore.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class TagDto {
    private Long id;

    private String name;

    private String description;

    private LocalDateTime time;

    private LibrarianDto librarian;
}
