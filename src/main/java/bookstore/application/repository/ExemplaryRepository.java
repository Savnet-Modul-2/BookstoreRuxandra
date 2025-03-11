package bookstore.application.repository;

import bookstore.application.entity.Exemplary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {
    List<Exemplary> findByBookId(Long bookId);

    @Query(value = """
            SELECT * FROM exemplary e
                    WHERE e.book_id = :bookId
                    AND e.id NOT IN (
                        SELECT r.exemplary_id FROM reservation r
                        WHERE r.exemplary_id = e.id
                        AND NOT (r.end_date < :startDate OR r.start_date > :endDate)
                    )
                    LIMIT 1
            """, nativeQuery = true)
    Exemplary findFirstAvailable(@Param("bookId") Long bookId,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate);
}
