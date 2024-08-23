package com.ranked.controller;

import com.ranked.dto.ReaderDTO;
import com.ranked.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @PostMapping
    public ReaderDTO createReader(@RequestBody ReaderDTO readerDTO) {
        return readerService.createReader(readerDTO);
    }

    @PutMapping("/{id}")
    public ReaderDTO updateReader(@PathVariable String id, @RequestBody ReaderDTO readerDTO) {
        return readerService.updateReader(id, readerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable String id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{fullName}")
    public List<ReaderDTO> findByFullName(@PathVariable String fullName) {
        return readerService.findByPersonFullName(fullName);
    }

    @GetMapping("/id/{personId}")
    public ResponseEntity<ReaderDTO> findByPersonId(@PathVariable String personId) {
        Optional<ReaderDTO> readerDTO = readerService.findById(personId);
        return readerDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/kind/{kindOfReader}")
    public List<ReaderDTO> findByKindOfReader(@PathVariable String kindOfReader) {
        return readerService.findByKindOfReader(kindOfReader);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDTO> findById(@PathVariable String id) {
        Optional<ReaderDTO> readerDTO = readerService.findById(id);
        return readerDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

