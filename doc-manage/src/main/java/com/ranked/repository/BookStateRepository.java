package com.ranked.repository;

import com.ranked.entity.Book;
import com.ranked.entity.BookState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ASUS TUF
 *
 */
@Repository
public interface BookStateRepository extends JpaRepository<BookState, Integer> {

	@Query("SELECT bs FROM BookState bs JOIN bs.borrowing b JOIN b.reader.person r ORDER BY r.fullName")
	List<BookState> findAllOrderByReaderName();

	List<BookState> findAllByOrderByQuantityAsc();

	@Query("SELECT bs FROM BookState bs JOIN bs.borrowing b JOIN b.reader.person r WHERE r.fullName = ?1")
	List<BookState> findByReaderName(String readerName);

	Optional<BookState> findById(Integer id);

	void deleteById(Integer id);

	@Query("SELECT bs.book FROM BookState bs ORDER BY bs.quantity DESC")
    Optional<Book> findMostBorrowedBook();

	@Query("SELECT bs.book FROM BookState bs " + "GROUP BY bs.book.id " + "ORDER BY SUM(bs.quantity) ASC")
	Optional<Book> findLeastBorrowedBook();
	
	
}
