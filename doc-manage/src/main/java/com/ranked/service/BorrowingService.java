package com.ranked.service;

import com.ranked.converter.BorrowingConverter;
import com.ranked.converter.ReaderConverter;
import com.ranked.dto.BorrowingDTO;
import com.ranked.dto.ReaderDTO;
import com.ranked.entity.Borrowing;
import com.ranked.entity.Reader;
import com.ranked.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BorrowingConverter borrowingConverter;
    
    @Autowired
    private ReaderConverter readerConverter;

    public BorrowingDTO createBorrowing(BorrowingDTO borrowingDTO) {
        Borrowing borrowing = borrowingConverter.toEntity(borrowingDTO);
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return borrowingConverter.toDTO(savedBorrowing);
    }

    public BorrowingDTO updateBorrowing(String id, BorrowingDTO borrowingDTO) {
        Optional<Borrowing> borrowingOptional = borrowingRepository.findById(id);
        if (borrowingOptional.isPresent()) {
            Borrowing borrowing = borrowingConverter.toEntity(borrowingDTO);
            borrowing.setId(id);
            Borrowing updatedBorrowing = borrowingRepository.save(borrowing);
            return borrowingConverter.toDTO(updatedBorrowing);
        } else {
            throw new RuntimeException("Borrowing not found with id " + id);
        }
    }

    public void deleteBorrowing(String id) {
        borrowingRepository.deleteById(id);
    }

    public List<BorrowingDTO> findByReaderName(String name) {
        List<Borrowing> borrowings = borrowingRepository.findByReaderName(name);
        return borrowings.stream()
                .map(borrowingConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReaderDTO> findReaderWhoBorrowedMostBooks() {
        Optional<Reader> reader = borrowingRepository.findReaderWhoBorrowedMostBooks();
        return reader.map(readerConverter::toDTO);
    }

    public Optional<ReaderDTO> findReaderWhoBorrowedLeastBooks() {
        Optional<Reader> reader = borrowingRepository.findReaderWhoBorrowedLeastBooks();
        return reader.map(readerConverter::toDTO);
    }

    public Optional<BorrowingDTO> findById(String id) {
        Optional<Borrowing> borrowingOptional = borrowingRepository.findById(id);
        return borrowingOptional.map(borrowingConverter::toDTO);
    }
}


