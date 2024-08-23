package com.ranked.converter;

import com.ranked.dto.BookStateDTO;
import com.ranked.entity.BookState;
import com.ranked.repository.BookRepository;
import com.ranked.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookStateConverter {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    public BookStateDTO toDTO(BookState bookState) {
        BookStateDTO dto = new BookStateDTO();
        dto.setId(bookState.getId());
        dto.setBookId(bookState.getBook().getId());
        dto.setBorrowingId(bookState.getBorrowing().getId());
        dto.setQuantity(bookState.getQuantity());
        dto.setState(bookState.getState());
        return dto;
    }

    public BookState toEntity(BookStateDTO dto) {
        BookState bookState = new BookState();
        bookState.setId(dto.getId());

        // Fetch the Book entity from the repository
        if (dto.getBookId() != null) {
            bookRepository.findById(dto.getBookId()).ifPresent(bookState::setBook);
        }

        // Fetch the Borrowing entity from the repository
        if (dto.getBorrowingId() != null) {
            borrowingRepository.findById(dto.getBorrowingId()).ifPresent(bookState::setBorrowing);
        }

        bookState.setQuantity(dto.getQuantity());
        bookState.setState(dto.getState());
        return bookState;
    }
}

