package com.ranked.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranked.entity.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

	Optional<Person> findById(String id);

	void deleteById(String id);
}
