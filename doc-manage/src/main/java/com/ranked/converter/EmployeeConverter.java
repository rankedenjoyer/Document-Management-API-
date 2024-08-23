package com.ranked.converter;

import com.ranked.dto.EmployeeDTO;
import com.ranked.entity.Employee;
import com.ranked.entity.Person;
import com.ranked.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeConverter {

    @Autowired
    private PersonRepository personRepository;

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setRole(employeeDTO.getRole());
        employee.setShift(employeeDTO.getShift());

        // Check if personId exists in PersonRepository
        Optional<Person> personOptional = personRepository.findById(employeeDTO.getPersonId());
        if (personOptional.isPresent()) {
            employee.setPerson(personOptional.get());
        } else {
            throw new RuntimeException("Person not found with id " + employeeDTO.getPersonId());
        }

        return employee;
    }
    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setRole(employee.getRole());
        employeeDTO.setShift(employee.getShift());
        employeeDTO.setPersonId(employee.getPerson().getId()); // Assuming you have a getId() method in Person entity
        return employeeDTO;
    }
    
    
}
