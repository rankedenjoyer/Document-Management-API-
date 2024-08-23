package com.ranked.service;

import com.ranked.entity.Document;
import com.ranked.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(String id, Document documentDetails) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            document.setTitle(documentDetails.getTitle());
            document.setPublishedYear(documentDetails.getPublishedYear());
            document.setQuantity(documentDetails.getQuantity());
            document.setAuthor(documentDetails.getAuthor());
            return documentRepository.save(document);
        } else {
            throw new RuntimeException("Document not found with id " + id);
        }
    }

    public void deleteDocument(String id) {
        documentRepository.deleteById(id);
    }

    public Optional<Document> findById(String id) {
        return documentRepository.findById(id);
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}
