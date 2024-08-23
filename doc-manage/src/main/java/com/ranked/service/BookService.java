package com.ranked.service;

import com.ranked.converter.BookConverter;
import com.ranked.dto.BookDTO;
import com.ranked.entity.Book;
import com.ranked.entity.Document;
import com.ranked.repository.BookRepository;
import com.ranked.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private BookConverter bookConverter;

    // Create a new book
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookConverter.dtoToEntity(bookDTO);
        book = bookRepository.save(book);
        return bookConverter.entityToDto(book);
    }

    // Update an existing book
    public BookDTO updateBook(String id, BookDTO bookDTO) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setType(bookDTO.getType());

            // Fetch and set the associated Document entity
            Document document = documentRepository.findById(bookDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found with id " + bookDTO.getDocumentId()));
            book.setDocument(document);

            book = bookRepository.save(book);
            return bookConverter.entityToDto(book);
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }

    // Delete a book by ID
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    // Find book by ID
    public Optional<BookDTO> findById(String id) {
        return bookRepository.findById(id).map(bookConverter::entityToDto);
    }

    // Find books by title
    public List<BookDTO> findByTitle(String title) {
        return bookRepository.findByDocumentTitle(title).stream().map(bookConverter::entityToDto).collect(Collectors.toList());
    }

    // Find books by author
    public List<BookDTO> findByAuthor(String author) {
        return bookRepository.findByDocumentAuthor(author).stream().map(bookConverter::entityToDto).collect(Collectors.toList());
    }

    // Find books by published year
    public List<BookDTO> findByPublishedYear(Integer publishedYear) {
        return bookRepository.findByDocumentPublishedYear(publishedYear).stream().map(bookConverter::entityToDto).collect(Collectors.toList());
    }

    // Find books by type
    public List<BookDTO> findByType(String type) {
        return bookRepository.findByType(type).stream().map(bookConverter::entityToDto).collect(Collectors.toList());
    }
}
