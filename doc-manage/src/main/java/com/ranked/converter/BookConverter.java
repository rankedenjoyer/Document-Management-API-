package com.ranked.converter;

import com.ranked.dto.BookDTO;
import com.ranked.entity.Book;
import com.ranked.entity.Document;
import com.ranked.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    @Autowired
    private DocumentRepository documentRepository;

    // Convert from Book entity to BookDTO
    public BookDTO entityToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setType(book.getType());
        bookDTO.setDocumentId(book.getDocument().getId());
        return bookDTO;
    }

    // Convert from BookDTO to Book entity
    public Book dtoToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setType(bookDTO.getType());

        // Fetch and set the associated Document entity
        Document document = documentRepository.findById(bookDTO.getDocumentId())
            .orElseThrow(() -> new RuntimeException("Document not found with id " + bookDTO.getDocumentId()));
        book.setDocument(document);

        return book;
    }
}
