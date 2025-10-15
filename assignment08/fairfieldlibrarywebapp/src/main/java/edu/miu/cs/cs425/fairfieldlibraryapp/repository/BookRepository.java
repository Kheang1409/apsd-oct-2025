package edu.miu.cs.cs425.fairfieldlibraryapp.repository;

import edu.miu.cs.cs425.fairfieldlibraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Custom query using JPQL
    @Query(value = "select b from Book b where b.isbn = :isbn")
    public abstract Book getBookByISBN(String isbn);

    // Using Query methods
//    public abstract List<Book> findBooksByTitleStartingWith(String titleStart);
//    List<Book> findBooksByTitleContainingAndAndDatePublished_MonthOrderByTitle(String title, LocalDate date);

}
