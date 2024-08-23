package com.ranked.service;

import com.ranked.converter.EmployeeConverter;
import com.ranked.dto.EmployeeDTO;
import com.ranked.entity.Employee;
import com.ranked.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeConverter employeeConverter;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeConverter.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeConverter.toDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setRole(employeeDTO.getRole());
            employee.setShift(employeeDTO.getShift());
            Employee updatedEmployee = employeeRepository.save(employee);
            return employeeConverter.toDTO(updatedEmployee);
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> findByPersonFullName(String fullName) {
        List<Employee> employees = employeeRepository.findByPersonFullName(fullName);
        return employees.stream()
                .map(employeeConverter::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> findByRole(String role) {
        List<Employee> employees = employeeRepository.findByRole(role);
        return employees.stream()
                .map(employeeConverter::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> findByShift(String shift) {
        List<Employee> employees = employeeRepository.findByShift(shift);
        return employees.stream()
                .map(employeeConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> findById(String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(employeeConverter::toDTO);
    }
}


