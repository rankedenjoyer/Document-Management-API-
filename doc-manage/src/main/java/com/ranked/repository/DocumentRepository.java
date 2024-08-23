package com.ranked.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranked.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, String> {

	Optional<Document> findById(String documentId);



	void deleteById(String id);
}
