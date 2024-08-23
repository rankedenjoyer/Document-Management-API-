package com.ranked.controller;

import com.ranked.dto.EmployeeDTO;
import com.ranked.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{fullName}")
    public List<EmployeeDTO> findByFullName(@PathVariable String fullName) {
        return employeeService.findByPersonFullName(fullName);
    }

    @GetMapping("/id/{personId}")
    public ResponseEntity<EmployeeDTO> findByPersonId(@PathVariable String personId) {
        Optional<EmployeeDTO> employeeDTO = employeeService.findById(personId);
        return employeeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public List<EmployeeDTO> findByRole(@PathVariable String role) {
        return employeeService.findByRole(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable String id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.findById(id);
        return employeeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

