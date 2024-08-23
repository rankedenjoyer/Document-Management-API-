package com.ranked.controller;

import com.ranked.dto.BookDTO;
import com.ranked.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    // Update an existing book
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable String id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // Find book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable String id) {
        Optional<BookDTO> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find books by title
    @GetMapping("/title/{title}")
    public List<BookDTO> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    // Find books by author
    @GetMapping("/author/{author}")
    public List<BookDTO> findByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }

    // Find books by published year
    @GetMapping("/year/{publishedYear}")
    public List<BookDTO> findByPublishedYear(@PathVariable Integer publishedYear) {
        return bookService.findByPublishedYear(publishedYear);
    }

    // Find books by type
    @GetMapping("/type/{type}")
    public List<BookDTO> findByType(@PathVariable String type) {
        return bookService.findByType(type);
    }
}

