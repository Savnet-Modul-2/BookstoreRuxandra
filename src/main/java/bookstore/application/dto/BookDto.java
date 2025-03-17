package bookstore.application.dto;

import bookstore.application.enums.Category;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class BookDto {
    private Long id;

    @NotNull
    private Long isbn;

    @NotNull
    private String title;

    @NotNull
    private String author;

    private LocalDate appearanceDate;

    private Integer nrOfPages;

    private Category category;

    private String language;

    private LibraryDto library;
}
