package bookstore.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity(name = "exemplary")
@Table(name = "exemplary", schema = "public")
public class Exemplary {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "MAXIMUM_RESERVATION_DURATION")
    private Integer maximumReservationDuration;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "exemplary")
    private List<Reservation> reservations = new ArrayList<>();

    public void add(Reservation reservation) {
        reservations.add(reservation);
        reservation.setExemplary(this);
    }
}
