package com.ranked.controller;

import com.ranked.dto.BookDTO;
import com.ranked.dto.BookStateDTO;
import com.ranked.service.BookStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookstates")
public class BookStateController {

    @Autowired
    private BookStateService bookStateService;

    // Create a new book state
    @PostMapping
    public ResponseEntity<BookStateDTO> createBookState(@RequestBody BookStateDTO bookStateDTO) {
        BookStateDTO createdBookState = bookStateService.createBookState(bookStateDTO);
        return ResponseEntity.ok(createdBookState);
    }

    // Update an existing book state
    @PutMapping("/{id}")
    public ResponseEntity<BookStateDTO> updateBookState(@PathVariable Integer id, @RequestBody BookStateDTO bookStateDTO) {
        BookStateDTO updatedBookState = bookStateService.updateBookState(id, bookStateDTO);
        return ResponseEntity.ok(updatedBookState);
    }

    // Delete a book state by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookState(@PathVariable Integer id) {
        bookStateService.deleteBookState(id);
        return ResponseEntity.noContent().build();
    }

    // Find book state by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookStateDTO> findById(@PathVariable Integer id) {
        Optional<BookStateDTO> bookState = bookStateService.findById(id);
        return bookState.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Sort book states by reader's name
    @GetMapping("/sort/reader")
    public ResponseEntity<List<BookStateDTO>> sortByReaderName() {
        List<BookStateDTO> bookStates = bookStateService.sortByReaderName();
        return ResponseEntity.ok(bookStates);
    }

    // Sort book states by quantity
    @GetMapping("/sort/quantity")
    public ResponseEntity<List<BookStateDTO>> sortByQuantity() {
        List<BookStateDTO> bookStates = bookStateService.sortByQuantity();
        return ResponseEntity.ok(bookStates);
    }

    // Find book states by reader's name
    @GetMapping("/reader/{readerName}")
    public ResponseEntity<List<BookStateDTO>> findByReaderName(@PathVariable String readerName) {
        List<BookStateDTO> bookStates = bookStateService.findByReaderName(readerName);
        return ResponseEntity.ok(bookStates);
    }

    // Find the most borrowed book
    @GetMapping("/most-borrowed")
    public ResponseEntity<BookDTO> findMostBorrowedBook() {
        Optional<BookDTO> mostBorrowedBook = bookStateService.findMostBorrowedBook();
        return mostBorrowedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find the least borrowed book
    @GetMapping("/least-borrowed")
    public ResponseEntity<BookDTO> findLeastBorrowedBook() {
        Optional<BookDTO> leastBorrowedBook = bookStateService.findLeastBorrowedBook();
        return leastBorrowedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

