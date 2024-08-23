package com.ranked.controller;

import com.ranked.entity.Person;
import com.ranked.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Create a new person
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    // Update an existing person
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable String id, @RequestBody Person personDetails) {
        try {
            Person updatedPerson = personService.updatePerson(id, personDetails);
            return ResponseEntity.ok(updatedPerson);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a person by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Find person by ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable String id) {
        Optional<Person> person = personService.findById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
