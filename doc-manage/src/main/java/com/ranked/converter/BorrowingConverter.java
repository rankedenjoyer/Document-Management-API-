package com.ranked.converter;

import com.ranked.dto.BorrowingDTO;
import com.ranked.entity.Borrowing;
import com.ranked.entity.Employee;
import com.ranked.entity.Reader;
import com.ranked.repository.EmployeeRepository;
import com.ranked.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowingConverter {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public BorrowingDTO toDTO(Borrowing borrowing) {
        BorrowingDTO dto = new BorrowingDTO();
        dto.setId(borrowing.getId());
        dto.setReaderId(borrowing.getReader().getId()); // Assuming Reader has getId method
        dto.setEmployeeId(borrowing.getEmployee().getId()); // Assuming Employee has getId method
        return dto;
    }

    public Borrowing toEntity(BorrowingDTO dto) {
        Borrowing borrowing = new Borrowing();
        borrowing.setId(dto.getId());

        // Fetching Reader and Employee entities from repositories
        Reader reader = readerRepository.findById(dto.getReaderId())
                .orElseThrow(() -> new RuntimeException("Reader not found with id: " + dto.getReaderId()));
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        borrowing.setReader(reader);
        borrowing.setEmployee(employee);

        // Assuming BookState is managed separately and IDs are managed correctly
        // You might need additional logic here based on your application's structure

        return borrowing;
    }
}
