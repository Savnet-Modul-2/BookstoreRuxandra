package bookstore.application.dto;

import bookstore.application.entity.Library;
import bookstore.application.enums.Category;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDto {
    private Long id;

    private Long isbn;

    private String title;

    private String author;

    private LocalDate appearanceDate;

    private Integer nrOfPages;

    private Category category;

    private String language;
}
