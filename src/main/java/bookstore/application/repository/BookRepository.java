package bookstore.application.repository;

import bookstore.application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = """
       SELECT book FROM book book
       WHERE (:author IS NULL OR book.author LIKE :author)
       AND (:title IS NULL OR book.title LIKE :title)
       """)
    List<Book> findByTitleAndAuthor(String author, String title);
}
