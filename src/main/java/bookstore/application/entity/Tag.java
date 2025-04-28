package bookstore.application.entity;

import jakarta.persistence.*;
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
@Entity(name = "tag")
@Table(name = "tag", schema = "public")
public class Tag {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "TIME")
    private LocalDateTime time;
}
