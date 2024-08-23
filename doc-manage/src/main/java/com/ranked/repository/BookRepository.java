package com.ranked.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranked.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE b.document.title = :title")
    List<Book> findByDocumentTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.document.author = :author")
    List<Book> findByDocumentAuthor(@Param("author") String author);

    @Query("SELECT b FROM Book b WHERE b.document.publishedYear = :publishedYear")
    List<Book> findByDocumentPublishedYear(@Param("publishedYear") Integer publishedYear);

    @Query("SELECT b FROM Book b WHERE b.document.id = :documentId")
    List<Book> findByDocumentId(@Param("documentId") String documentId);

    List<Book> findByType(String type);

    Optional<Book> findById(String id);

    void deleteById(String id); // Delete method
}
