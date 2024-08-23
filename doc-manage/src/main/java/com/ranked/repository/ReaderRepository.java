package com.ranked.repository;

import com.ranked.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, String> {

    @Query("SELECT r FROM Reader r WHERE r.person.fullName = ?1")
    List<Reader> findByPersonFullName(String fullName);

    Optional<Reader> findById(String id);

    List<Reader> findByKindOfReader(String kindOfReader);

    @Query("SELECT r FROM Reader r WHERE r.person.id = ?1")
    Optional<Reader> findByPersonId(String personId);

    void deleteById(String id);
}
