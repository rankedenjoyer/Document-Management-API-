package com.ranked.service;

import com.ranked.entity.Person;
import com.ranked.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Create a new person
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    // Update an existing person
    public Person updatePerson(String id, Person personDetails) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setFullName(personDetails.getFullName());
            person.setAddress(personDetails.getAddress());
            person.setGender(personDetails.getGender());
            person.setDob(personDetails.getDob());
            return personRepository.save(person);
        } else {
            throw new RuntimeException("Person not found with id " + id);
        }
    }

    // Delete a person by ID
    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }
}
