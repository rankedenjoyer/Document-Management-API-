package com.ranked.controller;

import com.ranked.dto.BorrowingDTO;
import com.ranked.dto.ReaderDTO;
import com.ranked.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    // Endpoint to create a new borrowing
    @PostMapping
    public ResponseEntity<BorrowingDTO> createBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        BorrowingDTO createdBorrowing = borrowingService.createBorrowing(borrowingDTO);
        return ResponseEntity.ok(createdBorrowing);
    }

    // Endpoint to update an existing borrowing
    @PutMapping("/{id}")
    public ResponseEntity<BorrowingDTO> updateBorrowing(@PathVariable String id, @RequestBody BorrowingDTO borrowingDTO) {
        BorrowingDTO updatedBorrowing = borrowingService.updateBorrowing(id, borrowingDTO);
        return ResponseEntity.ok(updatedBorrowing);
    }

    // Endpoint to find borrowings by reader's name
    @GetMapping("/reader/{name}")
    public ResponseEntity<List<BorrowingDTO>> getBorrowingsByReaderName(@PathVariable String name) {
        List<BorrowingDTO> borrowings = borrowingService.findByReaderName(name);
        return ResponseEntity.ok(borrowings);
    }

    // Endpoint to delete a borrowing by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable String id) {
        borrowingService.deleteBorrowing(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to find a borrowing by ID
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDTO> findBorrowingById(@PathVariable String id) {
        Optional<BorrowingDTO> borrowing = borrowingService.findById(id);
        return borrowing.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to find the reader who borrowed the most books
    @GetMapping("/reader/most-books")
    public ResponseEntity<ReaderDTO> getReaderWhoBorrowedMostBooks() {
        Optional<ReaderDTO> reader = borrowingService.findReaderWhoBorrowedMostBooks();
        return reader.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to find the reader who borrowed the least books
    @GetMapping("/reader/least-books")
    public ResponseEntity<ReaderDTO> getReaderWhoBorrowedLeastBooks() {
        Optional<ReaderDTO> reader = borrowingService.findReaderWhoBorrowedLeastBooks();
        return reader.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

