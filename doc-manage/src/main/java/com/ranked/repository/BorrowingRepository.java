package com.ranked.repository;

import com.ranked.entity.Borrowing;
import com.ranked.entity.Reader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, String> {
    
	@Query("SELECT b FROM Borrowing b WHERE b.reader.person.fullName = :name")
    List<Borrowing> findByReaderName(@Param("name") String name);

	@Query("SELECT b.reader " +
		       "FROM Borrowing b " +
		       "GROUP BY b.reader " +
		       "ORDER BY COUNT(b.id) ASC")
	Optional<Reader> findReaderWhoBorrowedLeastBooks();


    @Query("SELECT b.reader " +
            "FROM Borrowing b " +
            "GROUP BY b.reader " +
            "ORDER BY COUNT(b.id) DESC")
     Optional<Reader> findReaderWhoBorrowedMostBooks();

     Optional<Borrowing> findById(String id);

	void deleteById(String id);

}
