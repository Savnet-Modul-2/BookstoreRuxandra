package bookstore.application.entity;

import bookstore.application.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity(name = "book")
@Table(name = "book", schema = "public")
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ISBN")
    private Long isbn;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "APPEARANCE_DATE")
    private LocalDate appearanceDate;

    @Column(name = "NR_OF_PAGES")
    private Integer nrOfPages;

    @Column(name = "CATEGORY")
    private Category category;

    @Column(name = "LANGUAGE")
    private String language;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
}
