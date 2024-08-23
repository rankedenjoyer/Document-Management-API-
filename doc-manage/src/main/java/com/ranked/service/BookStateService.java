package com.ranked.service;

import com.ranked.converter.BookConverter;
import com.ranked.converter.BookStateConverter;
import com.ranked.dto.BookDTO;
import com.ranked.dto.BookStateDTO;
import com.ranked.entity.BookState;
import com.ranked.repository.BookStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStateService {

    @Autowired
    private BookStateRepository bookStateRepository;

    @Autowired
    private BookStateConverter bookStateConverter;

    @Autowired
    private BookConverter bookConverter;

    // Create a new book state
    public BookStateDTO createBookState(BookStateDTO bookStateDTO) {
        BookState bookState = bookStateConverter.toEntity(bookStateDTO);
        BookState savedBookState = bookStateRepository.save(bookState);
        return bookStateConverter.toDTO(savedBookState);
    }

    // Update an existing book state
    public BookStateDTO updateBookState(Integer id, BookStateDTO bookStateDTO) {
        Optional<BookState> bookStateOptional = bookStateRepository.findById(id);
        if (bookStateOptional.isPresent()) {
            BookState bookState = bookStateOptional.get();
            bookState.setBook(bookStateConverter.toEntity(bookStateDTO).getBook());
            bookState.setBorrowing(bookStateConverter.toEntity(bookStateDTO).getBorrowing());
            bookState.setQuantity(bookStateDTO.getQuantity());
            bookState.setState(bookStateDTO.getState());
            BookState updatedBookState = bookStateRepository.save(bookState);
            return bookStateConverter.toDTO(updatedBookState);
        } else {
            throw new RuntimeException("BookState not found with id " + id);
        }
    }

    // Delete a book state by ID
    public void deleteBookState(Integer id) {
        bookStateRepository.deleteById(id);
    }

    // Sort book states by reader's name
    public List<BookStateDTO> sortByReaderName() {
        return bookStateRepository.findAllOrderByReaderName()
                .stream()
                .map(bookStateConverter::toDTO)
                .collect(Collectors.toList());
    }

    // Sort book states by quantity
    public List<BookStateDTO> sortByQuantity() {
        return bookStateRepository.findAllByOrderByQuantityAsc()
                .stream()
                .map(bookStateConverter::toDTO)
                .collect(Collectors.toList());
    }

    // Find book states by reader's name
    public List<BookStateDTO> findByReaderName(String readerName) {
        return bookStateRepository.findByReaderName(readerName)
                .stream()
                .map(bookStateConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookStateDTO> findById(Integer id) {
        return bookStateRepository.findById(id)
                .map(bookStateConverter::toDTO);
    }

    // Find most borrowed book
    public Optional<BookDTO> findMostBorrowedBook() {
        return bookStateRepository.findMostBorrowedBook()
                .map(bookConverter::entityToDto);
    }

    // Find least borrowed book
    public Optional<BookDTO> findLeastBorrowedBook() {
        return bookStateRepository.findLeastBorrowedBook()
                .map(bookConverter::entityToDto);
    }
}
