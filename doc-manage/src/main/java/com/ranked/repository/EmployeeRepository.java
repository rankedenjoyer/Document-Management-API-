package com.ranked.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranked.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e WHERE e.person.fullName = :fullName")
    List<Employee> findByPersonFullName(@Param("fullName") String fullName);

    List<Employee> findByRole(String role);
    
    List<Employee> findByShift(String shift);

    Optional<Employee> findById(String id);

    void deleteById(String id); // Delete method
}
