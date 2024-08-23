package com.ranked.controller;

import com.ranked.entity.Document;
import com.ranked.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    @PutMapping("/{id}")
    public Document updateDocument(@PathVariable String id, @RequestBody Document documentDetails) {
        return documentService.updateDocument(id, documentDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> findById(@PathVariable String id) {
        Optional<Document> document = documentService.findById(id);
        return document.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Document> findAll() {
        return documentService.findAll();
    }
}
